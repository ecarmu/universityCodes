import torch
import torch.nn as nn
import torch.optim as optim
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import accuracy_score
from matplotlib.colors import ListedColormap
from sklearn.neighbors import KNeighborsClassifier

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
X_test = torch.FloatTensor(X_test)
y_test = torch.LongTensor(y_test)
# Define the simple neural network model
class SimpleModel(nn.Module):
    def __init__(self):
        super(SimpleModel, self).__init__()
        self.fc1 = nn.Linear(2, 6)
        self.fc2 = nn.Linear(6, 6)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = self.fc2(x)
        return x
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
# Function to train the model with specified regularization value
def train_model(model, X_train, y_train):
    # Set the model to training mode
    model.train()
    # Define loss function and optimizer
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=0.001)
    # Training loop
    epochs = 1000
    for epoch in range(epochs):
        optimizer.zero_grad()
        outputs = model(X_train)
        loss = criterion(outputs, y_train)
        loss.backward()
        optimizer.step()
# Train the simple model
model_simple = SimpleModel()
train_model(model_simple, X_train, y_train)
# Train the complex model
model_complex = ComplexModel()
train_model(model_complex, X_train, y_train)
# Evaluate model performance
def evaluate_model(model, X, y, dataset_name):
    # Set the model to evaluation mode
    model.eval()
    with torch.no_grad():
        outputs = model(X)
        _, predicted = torch.max(outputs, 1)
        accuracy = accuracy_score(y.numpy(), predicted.numpy())
        print(f"{dataset_name} Accuracy: {accuracy:.2%}")
        return accuracy
# Evaluate simple model on test set
test_accuracy_simple = evaluate_model(model_simple, X_test, y_test, "Simple Model (Test)")
# Evaluate complex model on test set
test_accuracy_complex = evaluate_model(model_complex, X_test, y_test, "Complex Model (Test)")
# Evaluate ideal model (KNN-based) on test data
model_ideal = KNeighborsClassifier(n_neighbors=5)
model_ideal.fit(X_train.numpy(), y_train.numpy())
test_accuracy_ideal = accuracy_score(y_test, model_ideal.predict(X_test.numpy()))
print(f"Ideal Model (Test) Accuracy: {test_accuracy_ideal:.2%}")
# Plotting decision boundaries for simple model
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
    # Plot test points
    plt.scatter(X[:, 0], X[:, 1], c=y, cmap=cmap, edgecolors='k')
    plt.title(title)
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.show()
# Plotting decision boundaries for simple model on test set
plt.figure(figsize=(8, 6))
plot_decision_boundary(model_simple, X_test, y_test, 'Simple Model Decision Boundaries (Test)')
# Plotting decision boundaries for complex model on test set
plt.figure(figsize=(8, 6))
plot_decision_boundary(model_complex, X_test, y_test, 'Complex Model Decision Boundaries (Test)')
# Plotting decision boundaries for ideal model (KNN-based) on test data
plt.figure(figsize=(8, 6))
cmap = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF', '#FFD700', '#8A2BE2', '#FF69B4'])
x_min, x_max = X_test[:, 0].min() - 1, X_test[:, 0].max() + 1
y_min, y_max = X_test[:, 1].min() - 1, X_test[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.1),
                     np.arange(y_min, y_max, 0.1))
Z = model_ideal.predict(np.c_[xx.ravel(), yy.ravel()])
Z = Z.reshape(xx.shape)
plt.contourf(xx, yy, Z, cmap=cmap, alpha=0.8)
plt.scatter(X_test[:, 0], X_test[:, 1], c=y_test, cmap=cmap, edgecolors='k')
plt.title('Ideal Model Decision Boundaries (KNN) (Test)')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.show()
