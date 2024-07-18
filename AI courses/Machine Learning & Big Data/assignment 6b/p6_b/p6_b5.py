import torch
import torch.nn as nn
import torch.optim as optim
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import accuracy_score

# Generate artificial dataset
classes = 6
m = 800
std = 0.4
centers = np.array([[-1, 0], [1, 0], [0, 1], [0, -1], [-2, 1], [-2, -1]])
X, y = make_blobs(n_samples=m, centers=centers, cluster_std=std,
                  random_state=2, n_features=2)
# Split dataset into training, cross-validation, and test sets
X_train, X_tmp, y_train, y_tmp = train_test_split(X, y, test_size=0.50,
                                                  random_state=1)
X_cv, X_test, y_cv, y_test = train_test_split(X_tmp, y_tmp, test_size=0.20,
                                              random_state=1)
# Convert numpy arrays to PyTorch tensors
X_train = torch.FloatTensor(X_train)
y_train = torch.LongTensor(y_train)
X_cv = torch.FloatTensor(X_cv)
y_cv = torch.LongTensor(y_cv)
# Define the complex neural network model
class ComplexModel(nn.Module):
    def __init__(self):
        super(ComplexModel, self).__init__()
        self.fc1 = nn.Linear(2, 120)
        self.fc2 = nn.Linear(120, 40)
        self.fc3 = nn.Linear(40, 6)
    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        x = self.fc3(x)
        return x
# Function to train and evaluate the model with specified regularization value
def train_and_evaluate_model(regularization_value):
    # Initialize the complex model
    model = ComplexModel()
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=0.001, weight_decay=regularization_value)  # L2 regularization
    # Training loop
    epochs = 1000
    train_errors = []
    cv_errors = []
    for epoch in range(epochs):
        optimizer.zero_grad()
        outputs = model(X_train)
        loss = criterion(outputs, y_train)
        loss.backward()
        optimizer.step()
        # Evaluate cross-validation error
        with torch.no_grad():
            outputs_cv = model(X_cv)
            _, predicted_cv = torch.max(outputs_cv, 1)
            cv_accuracy = accuracy_score(y_cv, predicted_cv)
            cv_error = 1.0 - cv_accuracy  # Error rate
            cv_errors.append(cv_error)
    # Final cross-validation error after all epochs
    final_cv_error = cv_errors[-1]
    # Evaluate final training error after all epochs
    with torch.no_grad():
        outputs_train = model(X_train)
        _, predicted_train = torch.max(outputs_train, 1)
        train_accuracy = accuracy_score(y_train, predicted_train)
        train_error = 1.0 - train_accuracy  # Error rate
    return final_cv_error, train_error
# List of regularization values to try
regularization_values = [0.0, 0.001, 0.01, 0.05, 0.1, 0.2, 0.3]
# Lists to store cross-validation errors and training errors for each regularization value
cv_errors = []
train_errors = []
# Iterate over regularization values and evaluate model performance
for reg_value in regularization_values:
    cv_error, train_error = train_and_evaluate_model(reg_value)
    cv_errors.append(cv_error)
    train_errors.append(train_error)
    print(f'Regularization Value: {reg_value}, Cross-validation Error: {cv_error:.4f}, Training Error: {train_error:.4f}')
# Plotting "Training Error" and "Cross-validation Error" vs "Regularization Value (λ)"
plt.figure(figsize=(8, 6))
plt.plot(regularization_values, train_errors, marker='o', linestyle='-', color='b', label='Training Error')
plt.plot(regularization_values, cv_errors, marker='s', linestyle='--', color='r', label='Cross-validation Error')
plt.title('Error vs Regularization Value')
plt.xlabel('Regularization Value (λ)')
plt.ylabel('Error')
plt.xscale('log')  # Use logarithmic scale for the x-axis
plt.grid(True)
plt.legend()
plt.show()
