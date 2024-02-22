# Generated by Django 4.1.3 on 2023-11-15 14:38

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("staff", "0005_staff_type_alter_staff_role"),
    ]

    operations = [
        migrations.AlterField(
            model_name="staff",
            name="type",
            field=models.PositiveSmallIntegerField(
                choices=[(0, "Helper"), (1, "Nurse"), (2, "Doctor")], null=True
            ),
        ),
    ]
