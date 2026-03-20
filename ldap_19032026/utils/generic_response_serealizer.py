from rest_framework import serializers

class GenericResponseSerializer(serializers.Serializer):
    statusCode = serializers.IntegerField(default=200)
    message = serializers.CharField()
    success = serializers.BooleanField(default=True)
    data = serializers.DictField(
        child=serializers.JSONField(),  # Permite cualquier contenido dentro de data
        help_text="Contenido de la respuesta, puede variar según la API"
    )
