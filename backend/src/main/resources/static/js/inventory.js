const products = [
    { name: "LAUNDRY DETERGENT", id: "001", reorderLevel: 15, stockLevel: 30 },
    { name: "FABRIC SOFTENER", id: "002", reorderLevel: 15, stockLevel: 25 },
    { name: "BLEACH", id: "003", reorderLevel: 10, stockLevel: 8 },
    { name: "PLASTIC WRAPS", id: "004", reorderLevel: 20, stockLevel: 0 },
    { name: "LAUNDRY BAGS", id: "005", reorderLevel: 20, stockLevel: 25 },
    { name: "MARKERS", id: "006", reorderLevel: 2, stockLevel: 2 },
    { name: "MASKING TAPE", id: "007", reorderLevel: 3, stockLevel: 0 },
    { name: "GASOLINE", id: "008", reorderLevel: 2, stockLevel: 1 },
    { name: "FINISHING SPRAY", id: "009", reorderLevel: 5, stockLevel: 10 },
    { name: "ADHESIVE TAPE", id: "010", reorderLevel: 3, stockLevel: 5 }
];

// 🧠 Dynamically compute stock status
products.forEach(product => {
    if (product.stockLevel === 0) {
        product.status = "OUT OF STOCK";
    } else if (product.stockLevel <= product.reorderLevel) {
        product.status = "LOW STOCK";
    } else {
        product.status = "IN STOCK";
    }
});

let filteredProducts = [...products];
let currentPage = 1;
const productsPerPage = 4;

function renderProducts() {
    const productContainer = document.querySelector('.product-container');
    productContainer.innerHTML = '';

    const startIndex = (currentPage - 1) * productsPerPage;
    const endIndex = startIndex + productsPerPage;
    const productsToDisplay = filteredProducts.slice(startIndex, endIndex);

    productsToDisplay.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');
    
        let statusClass = 'status';
        let statusIcon = '';
    
        if (product.status === 'IN STOCK') {
            statusClass += ' status-in-stock';
            statusIcon = '✅';
        } else if (product.status === 'LOW STOCK') {
            statusClass += ' status-low-stock';
            statusIcon = '⚠️';
        } else if (product.status === 'OUT OF STOCK') {
            statusClass += ' status-out-of-stock';
            statusIcon = '❌';
        }
    
        productCard.innerHTML = `
        <h2 class="product-name"><i class="fas fa-tag"></i> ${product.name}</h2>
        <p class="product-id"><i class="fas fa-barcode"></i> Product ID: ${product.id}</p>
        <p class="reorder-level"><i class="fas fa-arrow-rotate-left"></i> Reorder Level: ${product.reorderLevel}</p>
        <p class="stock-level"><i class="fas fa-boxes-stacked"></i> Available Stock Level: ${product.stockLevel}</p>
        <p class="${statusClass}">Status: ${statusIcon} ${product.status}</p>
    `;
    
        productContainer.appendChild(productCard);
    });
    

    updatePagination();
}

function filterProducts() {
    const searchTerm = document.getElementById('search-bar').value.toLowerCase();
    filteredProducts = products.filter(product => product.name.toLowerCase().includes(searchTerm));
    currentPage = 1;
    renderProducts();
}

function sortProducts() {
    const sortBy = document.getElementById('sort-by').value;

    if (sortBy === 'status') {
        const statusOrder = {
            "OUT OF STOCK": 1,
            "LOW STOCK": 2,
            "IN STOCK": 3
        };
        filteredProducts.sort((a, b) => statusOrder[a.status] - statusOrder[b.status]);
    } else if (sortBy === 'name') {
        filteredProducts.sort((a, b) => a.name.localeCompare(b.name));
    }

    currentPage = 1;
    renderProducts();
}

function changePage(direction) {
    currentPage += direction;
    if (currentPage < 1) currentPage = 1;
    if (currentPage > Math.ceil(filteredProducts.length / productsPerPage)) {
        currentPage = Math.ceil(filteredProducts.length / productsPerPage);
    }

    renderProducts();
}

function updatePagination() {
    const prevButton = document.getElementById('prev-button');
    const nextButton = document.getElementById('next-button');

    prevButton.disabled = currentPage === 1;
    nextButton.disabled = currentPage === Math.ceil(filteredProducts.length / productsPerPage);
}

window.onload = () => {
    renderProducts();
};