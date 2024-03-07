from django.test import TestCase, override_settings
from rest_framework.test import APIRequestFactory
from rest_framework import status
from request.views import (
    RequestListCreateView,
    RequestRetrieveUpdateDestroyView,
)
from request.models import Request
from staff.models import Staff
from tablet.models import Tablet
from unittest.mock import patch


@override_settings(
    REST_FRAMEWORK={
        "DEFAULT_AUTHENTICATION_CLASSES": [],
        "DEFAULT_PERMISSION_CLASSES": ["rest_framework.permissions.AllowAny"],
    }
)
class TestRequest(TestCase):
    def setUp(self):
        self.factory = APIRequestFactory()
        self.list_create_view = RequestListCreateView.as_view()
        self.retrieve_update_destroy_view = RequestRetrieveUpdateDestroyView.as_view()
        self.uri = "/request/"

        self.staff = Staff.objects.create(username="staff_user", type=Staff.Type.HELPER)
        self.tablet = Tablet.objects.create(name="Test Tablet")

        self.request = Request.objects.create(
            text="Test request",
            for_type=Staff.Type.HELPER,
            is_question=False,
            tablet=self.tablet,
            staff=self.staff,
        )

    @patch("request.models.Request.objects.all")
    def test_list_requests(self, mock_all):
        mock_all.return_value = [self.request]
        request = self.factory.get(self.uri)
        request.testing = True
        response = self.list_create_view(request)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("request.models.Request.objects.get")
    def test_retrieve_request(self, mock_get):
        mock_get.return_value = self.request
        uri = f"/request/{self.request.id}/"
        request = self.factory.get(uri)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.request.id)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("request.models.Request.objects.get")
    def test_update_request(self, mock_get):
        mock_get.return_value = self.request
        uri = f"/request/{self.request.id}/"
        data = {
            "text": "Updated request",
        }
        request = self.factory.patch(uri, data=data)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.request.id)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("request.models.Request.objects.get")
    def test_delete_request(self, mock_get):
        mock_get.return_value = self.request
        uri = f"/request/{self.request.id}/"
        request = self.factory.delete(uri)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.request.id)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)
