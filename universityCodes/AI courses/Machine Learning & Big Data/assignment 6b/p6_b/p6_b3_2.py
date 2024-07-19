import torch
import torch.nn as nn
import torch.optim as optim
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap

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
# Define the complex neural network model with regularization
class ComplexModelRegularized(nn.Module):
    def __init__(self):
        super(ComplexModelRegularized, self).__init__()
        self.fc1 = nn.Linear(2, 120)
        self.fc2 = nn.Linear(120, 40)
        self.fc3 = nn.Linear(40, 6)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        x = self.fc3(x)
        return x
# Initialize the complex model with regularization
model_complex_regularized = ComplexModelRegularized()
# Define loss function and optimizer with L2 regularization (weight decay)
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model_complex_regularized.parameters(), lr=0.001, weight_decay=0.1)
# Training loop
epochs = 1000
for epoch in range(epochs):
    optimizer.zero_grad()
    outputs = model_complex_regularized(X_train)
    loss = criterion(outputs, y_train)
    loss.backward()
    optimizer.step()
    if (epoch+1) % 100 == 0:
        print(f'Epoch [{epoch+1}/{epochs}], Loss: {loss.item():.4f}')
# Evaluate the complex model with regularization on cross-validation data
with torch.no_grad():
    outputs_cv = model_complex_regularized(X_cv)
    _, predicted_cv = torch.max(outputs_cv, 1)
    cv_accuracy = accuracy_score(y_cv, predicted_cv)
print(f'Complex Model with Regularization - Cross-validation accuracy: {cv_accuracy:.2%}')
# Function to plot decision boundaries of the model on specific data
def plot_decision_boundary(model, X, y, title):
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.1),
                         np.arange(y_min, y_max, 0.1))
    # Create input for the model
    grid_tensor = torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])
    Z = model(grid_tensor)
    _, Z = torch.max(Z, 1)
    Z = Z.reshape(xx.shape)
    # Create a color plot
    cmap = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF', '#FFD700', '#8A2BE2', '#FF69B4'])
    plt.contourf(xx, yy, Z, cmap=cmap, alpha=0.8)
    # Plot data points
    plt.scatter(X[:, 0], X[:, 1], c=y, cmap=cmap, edgecolors='k')
    plt.title(title)
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.show()
# Convert the model to evaluation mode
model_complex_regularized.eval()
# Plot decision boundaries of the complex model on the training data
plt.figure(figsize=(8, 6))
plot_decision_boundary(model_complex_regularized, X_train.numpy(), y_train.numpy(), 'Complex Model Decision Boundaries with Regularization - Training Data')
# Plot decison boundaries of the complex model on the cross-validation data
plt.figure(figsize=(8, 6))
plot_decision_boundary(model_complex_regularized, X_cv.numpy(), y_cv.numpy(), 'Complex Model Decision Boundaries with Regularization - Cross-Validation Data')