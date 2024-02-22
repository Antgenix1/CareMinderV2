# Generated by Django 4.1.3 on 2023-11-22 13:34

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ("request", "0004_alter_request_for_role"),
    ]

    operations = [
        migrations.CreateModel(
            name="ChatMessage",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("text", models.TextField()),
                ("time", models.DateTimeField(auto_now_add=True)),
                ("from_patient", models.BooleanField()),
                (
                    "request",
                    models.ForeignKey(
                        null=True,
                        on_delete=django.db.models.deletion.SET_NULL,
                        to="request.request",
                    ),
                ),
            ],
        ),
    ]
