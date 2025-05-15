document.addEventListener("DOMContentLoaded", () => {
    function parsePrice(value) {
      // Remove ₱ and commas, and convert to float
      return parseFloat(value.replace(/[₱,]/g, "")) || 0;
    }
  
    function updateTotal(quantityInputId, priceSelectId, totalGroupSelector) {
      const quantityInput = document.getElementById(quantityInputId);
      const priceSelect = document.getElementById(priceSelectId);
      const totalInput = document.querySelector(`${totalGroupSelector} input`);
  
      function calculate() {
        const quantity = parseFloat(quantityInput.value) || 0;
        const unitPrice = parsePrice(priceSelect.value);
        const total = quantity * unitPrice;
        totalInput.value = `₱${total.toFixed(2)}`;
      }
  
      quantityInput.addEventListener("input", calculate);
      priceSelect.addEventListener("change", calculate);
    }
  
    // Apply to each form section
    updateTotal("existing-quantity", "existing-priceUnit", ".order-section .input-group5");
    updateTotal("new-quantity", "new-priceUnit", ".order-section2 .input-group5");
  });
  