from django.test import TestCase, override_settings
from rest_framework.test import APIRequestFactory, force_authenticate
from patient.views import PatientListCreateView, PatientRetrieveUpdateDestroyView
from patient.models import Patient
from staff.models import Staff
from unittest.mock import patch


@override_settings(
    REST_FRAMEWORK={
        "DEFAULT_AUTHENTICATION_CLASSES": [],
        "DEFAULT_PERMISSION_CLASSES": ["rest_framework.permissions.AllowAny"],
    }
)
class TestPatient(TestCase):
    def setUp(self):
        self.factory = APIRequestFactory()
        self.view = PatientListCreateView.as_view()
        self.retrieve_view = PatientRetrieveUpdateDestroyView.as_view()
        self.uri = "/patient/"

        self.patient = Patient.objects.create(
            first_name="Test",
            last_name="Patient",
            age=30,
            severity=Patient.Severity.LOW,
            medical_progress=Patient.MedicalProgress.WAIT,
        )

    @patch("patient.models.Patient.objects.all")
    def test_list_patients(self, mock_all):
        mock_all.return_value = [self.patient]
        request = self.factory.get(self.uri)
        request.testing = True
        response = self.view(request)
        self.assertEqual(response.status_code, 200)

    @patch("patient.models.Patient.objects.get")
    def test_retrieve_patient(self, mock_get):
        mock_get.return_value = self.patient
        uri = f"/patient/{self.patient.id}/"
        request = self.factory.get(uri)
        request.testing = True
        response = self.retrieve_view(request, pk=self.patient.id)
        self.assertEqual(response.status_code, 200)

    @patch("patient.models.Patient.objects.get")
    def test_update_patient(self, mock_get):
        mock_get.return_value = self.patient
        uri = f"/patient/{self.patient.id}/"
        data = {
            "first_name": "Updated",
        }
        request = self.factory.put(uri, data=data)
        request.testing = True
        response = self.retrieve_view(request, pk=self.patient.id)
        self.assertEqual(response.status_code, 200)

    @patch("patient.models.Patient.objects.get")
    def test_delete_patient(self, mock_get):
        mock_get.return_value = self.patient
        uri = f"/patient/{self.patient.id}/"
        request = self.factory.delete(uri)
        request.testing = True
        response = self.retrieve_view(request, pk=self.patient.id)
        self.assertEqual(response.status_code, 204)
