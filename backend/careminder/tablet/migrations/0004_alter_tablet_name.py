# Generated by Django 4.1.3 on 2023-12-11 18:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("tablet", "0003_tablet_name"),
    ]

    operations = [
        migrations.AlterField(
            model_name="tablet",
            name="name",
            field=models.CharField(default="asdf", max_length=100),
            preserve_default=False,
        ),
    ]
