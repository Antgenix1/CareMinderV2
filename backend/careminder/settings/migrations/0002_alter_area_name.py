# Generated by Django 4.1.3 on 2024-01-28 18:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("settings", "0001_initial"),
    ]

    operations = [
        migrations.AlterField(
            model_name="area",
            name="name",
            field=models.CharField(max_length=100, unique=True),
        ),
    ]
