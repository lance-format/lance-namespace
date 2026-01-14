# coding: utf-8

"""
Tests for the header_provider functionality in Configuration and ApiClient.
"""

import unittest
from unittest.mock import Mock, patch, MagicMock

from lance_namespace_urllib3_client.configuration import Configuration
from lance_namespace_urllib3_client.api_client import ApiClient


class TestHeaderProvider(unittest.TestCase):
    """Tests for header_provider functionality"""

    def test_configuration_header_provider_default_none(self) -> None:
        """Test that header_provider defaults to None"""
        config = Configuration()
        self.assertIsNone(config.header_provider)

    def test_configuration_header_provider_can_be_set(self) -> None:
        """Test that header_provider can be set on Configuration"""
        config = Configuration()
        provider = lambda: {"Authorization": "Bearer test-token"}
        config.header_provider = provider
        self.assertEqual(config.header_provider, provider)

    def test_configuration_header_provider_callable(self) -> None:
        """Test that header_provider returns expected headers when called"""
        config = Configuration()
        expected_headers = {"Authorization": "Bearer test-token", "X-Custom": "value"}
        config.header_provider = lambda: expected_headers

        result = config.header_provider()
        self.assertEqual(result, expected_headers)

    def test_api_client_uses_header_provider(self) -> None:
        """Test that ApiClient calls header_provider and includes headers in requests"""
        config = Configuration()
        config.host = "http://localhost:8080"

        call_count = [0]
        def mock_provider():
            call_count[0] += 1
            return {"Authorization": f"Bearer token-{call_count[0]}"}

        config.header_provider = mock_provider

        client = ApiClient(configuration=config)

        # Mock the rest_client.request method
        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Make a call
        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={"Accept": "application/json"},
        )

        # Verify header_provider was called
        self.assertEqual(call_count[0], 1)

        # Verify the request was made with the provider headers
        call_args = client.rest_client.request.call_args
        headers = call_args.kwargs.get('headers') or call_args[1].get('headers')
        self.assertIn("Authorization", headers)
        self.assertEqual(headers["Authorization"], "Bearer token-1")

    def test_api_client_header_provider_called_each_request(self) -> None:
        """Test that header_provider is called for each request (for token refresh)"""
        config = Configuration()
        config.host = "http://localhost:8080"

        call_count = [0]
        def mock_provider():
            call_count[0] += 1
            return {"Authorization": f"Bearer token-{call_count[0]}"}

        config.header_provider = mock_provider

        client = ApiClient(configuration=config)

        # Mock the rest_client.request method
        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Make multiple calls
        for i in range(3):
            client.call_api(
                method="GET",
                url="http://localhost:8080/test",
                header_params={},
            )

        # Verify header_provider was called for each request
        self.assertEqual(call_count[0], 3)

    def test_api_client_no_header_provider(self) -> None:
        """Test that ApiClient works without header_provider"""
        config = Configuration()
        config.host = "http://localhost:8080"
        # header_provider is None by default

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Should not raise
        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={"Accept": "application/json"},
        )

        # Verify request was made
        self.assertTrue(client.rest_client.request.called)

    def test_api_client_header_provider_merges_with_existing(self) -> None:
        """Test that header_provider headers merge with existing headers"""
        config = Configuration()
        config.host = "http://localhost:8080"
        config.header_provider = lambda: {"Authorization": "Bearer token", "X-Provider": "yes"}

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Call with existing headers
        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={"Accept": "application/json", "X-Existing": "value"},
        )

        # Verify both existing and provider headers are present
        call_args = client.rest_client.request.call_args
        headers = call_args.kwargs.get('headers') or call_args[1].get('headers')
        self.assertEqual(headers["Accept"], "application/json")
        self.assertEqual(headers["X-Existing"], "value")
        self.assertEqual(headers["Authorization"], "Bearer token")
        self.assertEqual(headers["X-Provider"], "yes")

    def test_api_client_header_provider_empty_dict(self) -> None:
        """Test that header_provider returning empty dict works"""
        config = Configuration()
        config.host = "http://localhost:8080"
        config.header_provider = lambda: {}

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Should not raise
        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={"Accept": "application/json"},
        )

        self.assertTrue(client.rest_client.request.called)

    def test_api_client_header_provider_returns_none(self) -> None:
        """Test that header_provider returning None is handled gracefully"""
        config = Configuration()
        config.host = "http://localhost:8080"
        config.header_provider = lambda: None

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # Should not raise
        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={"Accept": "application/json"},
        )

        self.assertTrue(client.rest_client.request.called)


class TestHeaderProviderWithClass(unittest.TestCase):
    """Tests for header_provider using a class-based provider"""

    def test_class_based_header_provider(self) -> None:
        """Test using a class with get_headers method as provider"""
        class TokenProvider:
            def __init__(self, token: str):
                self.token = token
                self.call_count = 0

            def __call__(self):
                self.call_count += 1
                return {"Authorization": f"Bearer {self.token}"}

        config = Configuration()
        config.host = "http://localhost:8080"
        provider = TokenProvider("my-secret-token")
        config.header_provider = provider

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        client.call_api(
            method="GET",
            url="http://localhost:8080/test",
            header_params={},
        )

        self.assertEqual(provider.call_count, 1)

        call_args = client.rest_client.request.call_args
        headers = call_args.kwargs.get('headers') or call_args[1].get('headers')
        self.assertEqual(headers["Authorization"], "Bearer my-secret-token")

    def test_stateful_token_refresh_provider(self) -> None:
        """Test a stateful provider that simulates token refresh"""
        class RefreshingTokenProvider:
            def __init__(self):
                self.token_version = 0

            def __call__(self):
                self.token_version += 1
                return {"Authorization": f"Bearer token-v{self.token_version}"}

        config = Configuration()
        config.host = "http://localhost:8080"
        provider = RefreshingTokenProvider()
        config.header_provider = provider

        client = ApiClient(configuration=config)

        mock_response = MagicMock()
        mock_response.status = 200
        mock_response.data = b'{"result": "ok"}'
        mock_response.getheaders.return_value = {}
        client.rest_client.request = Mock(return_value=mock_response)

        # First request
        client.call_api(method="GET", url="http://localhost:8080/test", header_params={})
        call_args = client.rest_client.request.call_args
        headers = call_args.kwargs.get('headers') or call_args[1].get('headers')
        self.assertEqual(headers["Authorization"], "Bearer token-v1")

        # Second request - should have new token
        client.call_api(method="GET", url="http://localhost:8080/test", header_params={})
        call_args = client.rest_client.request.call_args
        headers = call_args.kwargs.get('headers') or call_args[1].get('headers')
        self.assertEqual(headers["Authorization"], "Bearer token-v2")


if __name__ == '__main__':
    unittest.main()
