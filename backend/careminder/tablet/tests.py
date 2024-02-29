from django.test import TestCase, override_settings
from rest_framework.test import APIRequestFactory
from rest_framework import status
from tablet.views import (
    TabletListCreateView,
    TabletRetrieveUpdateDestroyView,
)
from tablet.models import Tablet
from patient.models import Patient
from settings.models import Area
from unittest.mock import patch


@override_settings(
    REST_FRAMEWORK={
        "DEFAULT_AUTHENTICATION_CLASSES": [],
        "DEFAULT_PERMISSION_CLASSES": ["rest_framework.permissions.AllowAny"],
    }
)
class TestTablet(TestCase):
    def setUp(self):
        self.factory = APIRequestFactory()
        self.list_create_view = TabletListCreateView.as_view()
        self.retrieve_update_destroy_view = TabletRetrieveUpdateDestroyView.as_view()
        self.uri = "/tablet/"

        self.area = Area.objects.create(name="Test Area")
        self.patient = Patient.objects.create(first_name="Test", last_name="Patient")

        self.tablet = Tablet.objects.create(name="Test Tablet", area=self.area)

    @patch("tablet.models.Tablet.objects.all")
    def test_list_tablets(self, mock_all):
        mock_all.return_value = [self.tablet]
        request = self.factory.get(self.uri)
        request.testing = True
        response = self.list_create_view(request)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("tablet.models.Tablet.objects.get")
    def test_retrieve_tablet(self, mock_get):
        mock_get.return_value = self.tablet
        uri = f"/tablet/{self.tablet.id}/"
        request = self.factory.get(uri)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.tablet.id)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("tablet.models.Tablet.objects.get")
    def test_update_tablet(self, mock_get):
        mock_get.return_value = self.tablet
        uri = f"/tablet/{self.tablet.id}/"
        data = {
            "name": "Updated Tablet",
        }
        request = self.factory.put(uri, data=data)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.tablet.id)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    @patch("tablet.models.Tablet.objects.get")
    def test_delete_tablet(self, mock_get):
        mock_get.return_value = self.tablet
        uri = f"/tablet/{self.tablet.id}/"
        request = self.factory.delete(uri)
        request.testing = True
        response = self.retrieve_update_destroy_view(request, pk=self.tablet.id)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)
