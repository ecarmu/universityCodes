import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import torch
import torch.nn as nn
import torch.optim as optim
from sklearn.metrics import accuracy_score

# Generate artificial data
classes = 6
m = 800
std = 0.4
centers = np.array([[-1, 0], [1, 0], [0, 1], [0, -1], [-2, 1], [-2, -1]])
X, y = make_blobs(n_samples=m, centers=centers, cluster_std=std, random_state=2, n_features=2)

# Split the data
X_train, X_, y_train, y_ = train_test_split(X, y, test_size=0.50, random_state=1)
X_cv, X_test, y_cv, y_test = train_test_split(X_, y_, test_size=0.20, random_state=1)

'''
# Plotting
plt.figure(figsize=(12, 6))

# Plot training data and cross-validation data on the left
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap='viridis', marker='o', label='Training Data')
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=y_cv, cmap='viridis', marker='^', label='Cross-Validation Data')
plt.title('Training and Cross-Validation Data')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()


# Plotting the ideal model on the right
plt.subplot(1, 2, 2)
# Assuming you have an ideal model represented by lines, you can plot them here
# For simplicity, let's say it's just a plot of the clusters without overlap
plt.scatter(X[:, 0], X[:, 1], c=y, cmap='viridis')
plt.title('Ideal Model')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')

plt.tight_layout()
plt.show()


# Calculate equal distance boundaries
boundaries = []
for i in range(len(centers)):
    for j in range(i+1, len(centers)):
        center1 = centers[i]
        center2 = centers[j]
        midpoint = (center1 + center2) / 2
        direction = center2 - center1
        direction = direction.astype(float)  # Convert direction to float
        norm = float(np.sqrt(np.sum(direction ** 2)))
        direction /= norm
        boundaries.append((midpoint, direction))



# Classify points based on boundaries
def classify_point(point, boundaries):
    for boundary in boundaries:
        midpoint, direction = boundary
        if np.dot(point - midpoint, direction) < 0:
            return 0  # Classify as 0
    return 1  # Classify as 1

# Classify all points
y_pred = np.array([classify_point(point, boundaries) for point in X])

# Calculate misclassification rate
misclassification_rate = 1 - accuracy_score(y, y_pred)

print("Misclassification rate:", misclassification_rate)
'''

# Define the neural network architecture
model = nn.Sequential(
    nn.Linear(2, 120),
    nn.ReLU(),
    nn.Linear(120, 40),
    nn.ReLU(),
    nn.Linear(40, 6)
)   

# Define the loss function
criterion = nn.CrossEntropyLoss()

# Define the optimizer
optimizer = optim.Adam(model.parameters(), lr=0.001)

# Assuming you have your data in the format X_train and y_train
# Convert them to PyTorch tensors
X_train_tensor = torch.tensor(X_train, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train, dtype=torch.long)

# Train the neural network
num_epochs = 1000
for epoch in range(num_epochs):
    # Forward pass
    outputs = model(X_train_tensor)
    loss = criterion(outputs, y_train_tensor)

    # Backward pass and optimization
    loss.backward()
    optimizer.step()
    optimizer.zero_grad()
    

    # Print progress
    if (epoch+1) % 100 == 0:
        print(f'Epoch [{epoch+1}/{num_epochs}], Loss: {loss.item():.4f}')

print('Training finished.')

# Convert them to PyTorch tensors
X_cv_tensor = torch.tensor(X_cv, dtype=torch.float32)
y_cv_tensor = torch.tensor(y_cv, dtype=torch.long)

outputs = model(X_cv_tensor)

# Get the predicted labels
_, predicted_labels = torch.max(outputs, 1)

# Calculate the miscategorization rate
num_miscategorized = (predicted_labels != y_cv_tensor).sum().item()
miscategorization_rate = num_miscategorized / len(y_cv)

print(f'Miscategorization Rate on CV Data: {miscategorization_rate:.2%}')
