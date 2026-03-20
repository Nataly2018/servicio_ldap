from django import forms

class ExisteUsuarioForm(forms.Form)  :
    nombreUsuario = forms.CharField(min_length=1, max_length=50)

    def clean_nombreusuario(self):
        nombre = self.cleaned_data.get('nombreUsuario')
        if "Gabriel" in nombre:
            raise forms.ValidationError("El nombre no puede contener 'Gabriel'.")
        return nombre