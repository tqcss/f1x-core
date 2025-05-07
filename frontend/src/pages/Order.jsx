import React from 'react';
import './styles/orders.css'; // Ensure this path matches your project structure
import logo from './logo.png';
import homeButton from './assets/images/homeButton.png';
import orderButton from './assets/images/orderButton.png';
import inventoryButton from './assets/images/inventoryButton.png';
import supplierButton from './assets/images/supplierButton.png';
import employeesButton from './assets/images/employeesButton.png';
import trackingButton from './assets/images/trackingButton.png';
import financeButton from './assets/images/financeButton.png';
import reportsButton from './assets/images/reportsButton.png';
import accountButton from './assets/images/accountButton.png';
import logoutButton from '.assets/images/logoutButton.png';

const Order = () => {
  return (
    <div>
      <header>
        <img
          className="Logo"
          onClick={() => window.location.href = 'home.html'}
          src={logo}
          alt=""
        />
        <nav>
          <ul className="sidebar">
            <li><a href="home.html"><img src={homeButton} alt="Home" /></a></li>
            <li><a href="orders.html"><img src={orderButton} alt="Order" /></a></li>
            <li><a href="inventory.html"><img src={inventoryButton} alt="Inventory" /></a></li>
            <li><a href="supplier.html"><img src={supplierButton} alt="Supplier" /></a></li>
            <li><a href="employee.html"><img src={employeesButton} alt="Employee" /></a></li>
            <li><a href="tracking.html"><img src={trackingButton} alt="Tracking" /></a></li>
            <li><a href="finance.html"><img src={financeButton} alt="Finance" /></a></li>
            <li><a href="reports.html"><img src={reportsButton} alt="Reports" /></a></li>
            
            <li className="spaced-top"><a href="login.html"><img src={accountButton} alt="Account" /></a></li>
            <li><a href="logout.html"><img src={logoutButton} alt="Logout" /></a></li>
          </ul>
        </nav>
      </header>

      <div className="main-content">
        <div className="title-header">
          <h1>ORDERS</h1>
        </div>
      </div>

      <button className="add-order" onClick={() => window.location.href = 'orders.html'}>+Add Order</button>
      <button className="order-table" onClick={() => window.location.href = 'orders1.html'}>Order Table</button>

      {/* Form for Orders */}
      <div className="order-container">
        {/* Existing Customers */}
        <div className="order-left">
          <div className="order-section">
            <h2>For Existing Customers</h2>
            <div className="input-group">
              <label>Customer Name</label>
              <input type="text" placeholder="Enter name" />
            </div>

            <div className="input-group2">
              <label>Service:</label>
              <label><input type="radio" name="existing-service" value="pickup" /> Pick-up</label>
              <label><input type="radio" name="existing-service" value="delivery" /> Delivery</label>
            </div>

            <div className="input-group3">
              <label>Quantity</label>
              <input type="number" id="existing-quantity" placeholder="kg" />
            </div>

            <div className="input-group4">
              <label htmlFor="priceUnit">Price Unit</label>
              <select id="existing-priceUnit">
                <option value="₱00.00">₱00.00</option>
                <option value="₱10.00">₱10.00</option>
                <option value="₱20.00">₱20.00</option>
                <option value="₱50.00">₱50.00</option>
                <option value="₱100.00">₱100.00</option>
              </select>
            </div>

            <div className="input-group5">
              <label>Total</label>
              <input type="text" value="₱00.00" readOnly />
            </div>
          </div>

          {/* Non-Existing Customers */}
          <div className="order-section2">
            <h2>For Non-Existing Customers</h2>
            <div className="input-group">
              <label>Customer Name</label>
              <input type="text" placeholder="Enter name" />
              <label>Contact Number</label>
              <input type="text" placeholder="09XXXXXXXXX" />
            </div>

            <div className="input-group2">
              <label>Service</label>
              <label><input type="radio" name="new-service" value="pickup" /> Pick-up</label>
              <label><input type="radio" name="new-service" value="delivery" /> Delivery</label>
            </div>

            <div className="input-group3">
              <label>Quantity</label>
              <input type="number" id="new-quantity" placeholder="kg" />
            </div>

            <div className="input-group4">
              <label htmlFor="priceUnit">Price Unit</label>
              <select id="new-priceUnit">
                <option value="₱00.00">₱00.00</option>
                <option value="₱10.00">₱10.00</option>
                <option value="₱20.00">₱20.00</option>
                <option value="₱50.00">₱50.00</option>
                <option value="₱100.00">₱100.00</option>
              </select>
            </div>

            <div className="input-group5">
              <label>Total</label>
              <input type="text" value="₱00.00" readOnly />
              <button className="generate-receipt">Generate Receipt</button>
            </div>
          </div>
        </div>

        {/* Complete Details Section */}
        <div className="order-right">
          <h3>Complete Details</h3>
          <div className="customer-details">
            <h4>Customer Details</h4>
          </div>
          <div className="input-group6">
            <label>Name</label>
            <input type="text" readOnly />
          </div>
          <div className="input-id">
            <label>ORDER NO:</label>
            <input type="text" readOnly />
          </div>
          <div className="input-group6">
            <label>Contact Number</label>
            <input type="text" readOnly />
          </div>
          <div className="input-group7">
            <label>Address</label>
            <input type="text" readOnly />
          </div>
          <div className="order-details">
            <h5>Order Details</h5>
          </div>
          <div className="input-group8">
            <label>Service</label>
            <input type="text" readOnly />
          </div>
          <div className="input-group8">
            <label>Quantity (Kg)</label>
            <input type="text" readOnly />
          </div>
          <div className="input-group9">
            <label>Total:</label>
            <input type="text" value="₱000.00" readOnly />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Order;
