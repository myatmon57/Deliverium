const imageChooser = document.getElementById('image-chooser');
const imagePreview = document.getElementById('image-preview');

imageChooser.addEventListener('change', function() {
  const file = this.files[0];

  if (file) {
    const reader = new FileReader();

    reader.onload = function(e) {
      imagePreview.src = e.target.result;
      imagePreview.style.display = 'block';
    };

    reader.readAsDataURL(file);
  } else {
    imagePreview.src = "";
    imagePreview.style.display = 'none';
  }
});
