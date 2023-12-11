# Generated by Django 4.1.3 on 2023-12-10 19:48

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Area",
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
                ("name", models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name="Settings",
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
                (
                    "hospital_title",
                    models.CharField(blank=True, max_length=100, null=True),
                ),
                (
                    "hospital_description",
                    models.CharField(blank=True, max_length=255, null=True),
                ),
                (
                    "notification",
                    models.CharField(blank=True, max_length=255, null=True),
                ),
                ("use_nfc", models.BooleanField(default=False)),
            ],
        ),
    ]
