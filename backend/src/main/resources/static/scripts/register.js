document.addEventListener('DOMContentLoaded', () => {
    const closeButton = document.querySelector('.exit-alert-button');
    const alertBox = document.querySelector('.success-alert');

    if (closeButton && alertBox) {
        closeButton.addEventListener('click', () => {
            alertBox.classList.add('fade-out');

            setTimeout(() => {
                alertBox.style.display = 'none';
            }, 700);
        });
    }
});
