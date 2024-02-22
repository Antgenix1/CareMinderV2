# Generated by Django 4.1.3 on 2023-11-02 20:09

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Request",
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
                ("recording", models.BinaryField(null=True)),
                (
                    "for_role",
                    models.PositiveSmallIntegerField(
                        choices=[
                            (0, "Admin"),
                            (1, "Secretary"),
                            (2, "Helper"),
                            (3, "Nurse"),
                            (4, "Doctor"),
                        ]
                    ),
                ),
                ("is_question", models.BooleanField()),
                (
                    "state",
                    models.PositiveSmallIntegerField(
                        choices=[(0, "Waiting"), (1, "Processing"), (2, "Finished")],
                        default=0,
                    ),
                ),
                ("time", models.DateTimeField(auto_now_add=True)),
                ("response", models.TextField(blank=True, null=True)),
                ("response_time", models.DateTimeField(null=True)),
            ],
        ),
    ]
