def join_serializer_errors(serializer, separator=", "):
    messages = []

    errors = getattr(serializer, "errors", {})
    
    if isinstance(errors, dict):
        for field_name, field_errors in errors.items():
            if field_errors:
                # Si field_errors es lista, convertir a strings
                if isinstance(field_errors, list):
                    messages.extend([f"{field_name}: {str(e)}" for e in field_errors])
                else:
                    messages.append(f"{field_name}: {str(field_errors)}")
    elif isinstance(errors, list):
        # errores generales no asociados a campo específico
        messages.extend([str(e) for e in errors])
    else:
        # cualquier otro tipo
        messages.append(str(errors))
    return separator.join(messages)
