// import React from 'react';
import '../styles/orders.css'; // Ensure this path matches your project structure
import logo from '../../public/assets/logo.png';
import homeButton from '../../public/assets/images/homeButton.png';
import orderButton from '../../public/assets/images/orderButton.png';
import inventoryButton from '../../public/assets/images/inventoryButton.png';
import supplierButton from '../../public/assets/images/supplierButton.png';
import employeesButton from '../../public/assets/images/employeesButton.png';
import trackingButton from '../../public/assets/images/trackingButton.png';
import financeButton from '../../public/assets/images/financeButton.png';
import reportsButton from '../../public/assets/images/reportsButton.png';
import accountButton from '../../public/assets/images/accountButton.png';
import logoutButton from '../../public/assets/images/logoutButton.png';
import React, { useState, useEffect } from 'react';

function Order() {
  return (
    <div>
      <header>
        <img
          className="Logo"
          // onClick={() => window.location.href = 'home.html'}
          src={logo}
          alt=""
        />
        <nav>
          <ul className="sidebar">
            <li><a href=""><img src={homeButton} alt="Home" /></a></li>
            <li><a href=""><img src={orderButton} alt="Order" /></a></li>
            <li><a href=""><img src={inventoryButton} alt="Inventory" /></a></li>
            <li><a href=""><img src={supplierButton} alt="Supplier" /></a></li>
            <li><a href=""><img src={employeesButton} alt="Employee" /></a></li>
            <li><a href=""><img src={trackingButton} alt="Tracking" /></a></li>
            <li><a href=""><img src={financeButton} alt="Finance" /></a></li>
            <li><a href=""><img src={reportsButton} alt="Reports" /></a></li>
            
            <li className="spaced-top"><a href=""><img src={accountButton} alt="Account" /></a></li>
            <li><a href=""><img src={logoutButton} alt="Logout" /></a></li>
          </ul>
        </nav>
      </header>

      <div className="main-content">
        <div className="title-header">
          <h1>ORDERS</h1>
        </div>
      </div>

      <button className="add-order" onClick={() => window.location.href = ''}>+Add Order</button>
      <button className="order-table" onClick={() => window.location.href = ''}>Order Table</button>

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
