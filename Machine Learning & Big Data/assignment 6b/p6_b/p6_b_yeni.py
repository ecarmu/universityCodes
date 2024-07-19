import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import torch
import torch.nn as nn
import torch.optim as optim
from sklearn.metrics import accuracy_score
from matplotlib.colors import ListedColormap
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


# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap='viridis', marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap='viridis', marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

plt.tight_layout()
plt.show()


# Define a custom colormap
cmap = ListedColormap(['red', 'green', 'blue', 'orange', 'purple', 'yellow'])

# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap, marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot decision boundaries for Training Data Classification
h = 0.02  # step size in the mesh
x_min, x_max = X_train[:, 0].min() - 1, X_train[:, 0].max() + 1
y_min, y_max = X_train[:, 1].min() - 1, X_train[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                     np.arange(y_min, y_max, h))
Z = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z, alpha=0.5, cmap=cmap)

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap=cmap, marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot decision boundaries for Cross-validation Data Classification
Z_cv = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z_cv, alpha=0.5, cmap=cmap)

plt.tight_layout()
plt.show()
'''

'''
model = nn.Sequential(
    nn.Linear(2, 6),
    nn.ReLU(),
    nn.Linear(6, 40),
    nn.ReLU(),
    nn.Linear(40, 6)
)

criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

num_epoch = 1000
X_train_tensor = torch.tensor(X_train, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train, dtype=torch.long)

for epoch in range(num_epoch):
    output = model(X_train_tensor)
    loss = criterion(output, y_train_tensor)
    loss.backward()
    optimizer.step()
    optimizer.zero_grad()

    # Print progress
    if (epoch+1) % 100 == 0:
        print(f'Epoch [{epoch+1}/{num_epoch}], Loss: {loss.item():.4f}')

print('Training finished.')

# Convert them to PyTorch tensors
X_cv_tensor = torch.tensor(X_cv, dtype=torch.float32)
y_cv_tensor = torch.tensor(y_cv, dtype=torch.long)

outputs = model(X_cv_tensor)

# Get the predicted labels for training data
outputs_train = model(X_train_tensor)
_, predicted_labels_train = torch.max(outputs_train, 1)

# Calculate the misclassification rate on training data
num_miscategorized_train = (predicted_labels_train != y_train_tensor).sum().item()
miscategorization_rate_train = num_miscategorized_train / len(y_train)

print(f'Miscategorization Rate on Training Data: {miscategorization_rate_train:.2%}')




# Get the predicted labels
_, predicted_labels = torch.max(outputs, 1)

# Calculate the miscategorization rate
num_miscategorized = (predicted_labels != y_cv_tensor).sum().item()
miscategorization_rate = num_miscategorized / len(y_cv)

print(f'Miscategorization Rate on CV Data: {miscategorization_rate:.2%}')


# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap='viridis', marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap='viridis', marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

plt.tight_layout()
plt.show()


# Define a custom colormap
cmap = ListedColormap(['red', 'green', 'blue', 'orange', 'purple', 'yellow'])

# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap, marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot decision boundaries for Training Data Classification
h = 0.02  # step size in the mesh
x_min, x_max = X_train[:, 0].min() - 1, X_train[:, 0].max() + 1
y_min, y_max = X_train[:, 1].min() - 1, X_train[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                     np.arange(y_min, y_max, h))
Z = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z, alpha=0.5, cmap=cmap)

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap=cmap, marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Plot decision boundaries for Cross-validation Data Classification
Z_cv = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z_cv, alpha=0.5, cmap=cmap)

plt.tight_layout()
plt.show()
'''


# Define the neural network architecture
model = nn.Sequential(
    nn.Linear(2, 6),
    nn.ReLU(),
    nn.Linear(6, 40),
    nn.ReLU(),
    nn.Linear(40, 6)
)

# Define the loss function
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

# Train the model
num_epochs = 1000
X_train_tensor = torch.tensor(X_train, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train, dtype=torch.long)

for epoch in range(num_epochs):
    output = model(X_train_tensor)
    loss = criterion(output, y_train_tensor)
    loss.backward()
    optimizer.step()
    optimizer.zero_grad()

    # Print progress
    if (epoch+1) % 100 == 0:
        print(f'Epoch [{epoch+1}/{num_epochs}], Loss: {loss.item():.4f}')

print('Training finished.')

# Convert validation data to PyTorch tensors
X_cv_tensor = torch.tensor(X_cv, dtype=torch.float32)
y_cv_tensor = torch.tensor(y_cv, dtype=torch.long)
# Get predictions for validation data
outputs = model(X_cv_tensor)

# Get the predicted labels
_, predicted_labels = torch.max(outputs, 1)

# Calculate the misclassification rate
num_miscategorized = (predicted_labels != y_cv_tensor).sum().item()
miscategorization_rate = num_miscategorized / len(y_cv)

print(f'Miscategorization Rate on CV Data: {miscategorization_rate:.2%}')

# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap='viridis', marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap='viridis', marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')

plt.tight_layout()
plt.show()


# Define a custom colormap
cmap = ListedColormap(['red', 'green', 'blue', 'orange', 'purple', 'yellow'])

# Plot Training Data Classification
plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=cmap, marker='o')
plt.title('Training Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')

# Plot decision boundaries for Training Data Classification
h = 0.02  # step size in the mesh
x_min, x_max = X_train[:, 0].min() - 1, X_train[:, 0].max() + 1
y_min, y_max = X_train[:, 1].min() - 1, X_train[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                     np.arange(y_min, y_max, h))
Z = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z, alpha=0.5, cmap=cmap)

# Plot Cross-validation Data Classification
plt.subplot(1, 2, 2)
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=predicted_labels.numpy(), cmap=cmap, marker='o')
plt.title('Cross-validation Data Classification')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')

# Plot decision boundaries for Cross-validation Data Classification
Z_cv = model(torch.FloatTensor(np.c_[xx.ravel(), yy.ravel()])).detach().argmax(dim=1).reshape(xx.shape)
plt.contourf(xx, yy, Z_cv, alpha=0.5, cmap=cmap)

plt.tight_layout()
plt.show()






'''

# Define regularization values to try

criterion = nn.CrossEntropyLoss()

# Convert data to PyTorch tensors
X_train_tensor = torch.tensor(X_train, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train, dtype=torch.long)
X_cv_tensor = torch.tensor(X_cv, dtype=torch.float32)
y_cv_tensor = torch.tensor(y_cv, dtype=torch.long)

# Define regularization values to try
regularization_values = [0.0, 0.001, 0.01, 0.05, 0.1, 0.2, 0.3]

# Lists to store misclassification rates
miscategorization_rates_train = []
miscategorization_rates_cv = []

X_train_tensor = torch.tensor(X_train, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train, dtype=torch.long)
num_epochs = 1000


miscategorization_rates_train = []
miscategorization_rates_cv = []

# Variable to store the lowest error rate giving lambda
min_error_rate_lambda = None
min_error_rate_train = float('inf')
min_error_rate_cv = float('inf')

for regularization_value in regularization_values:
    # Define the neural network architecture with current regularization value
    model_regularized = nn.Sequential(
        nn.Linear(2, 120),
        nn.ReLU(),
        nn.Linear(120, 40),
        nn.ReLU(),
        nn.Linear(40, 6)
    )   

    # Define the optimizer with L2 regularization (weight_decay parameter)
    optimizer_regularized = optim.Adam(model_regularized.parameters(), lr=0.001, weight_decay=regularization_value)

    # Train the neural network with current regularization value
    for epoch in range(num_epochs):
        # Forward pass
        outputs = model_regularized(X_train_tensor)
        loss = criterion(outputs, y_train_tensor)

        # Backward pass and optimization
        loss.backward()
        optimizer_regularized.step()
        optimizer_regularized.zero_grad()

    outputs_train = model_regularized(X_train_tensor)
    _, predicted_labels_train = torch.max(outputs_train, 1)
    num_miscategorized_train = (predicted_labels_train != y_train_tensor).sum().item()
    miscategorization_rate_train = num_miscategorized_train / len(y_train)

    outputs_cv = model_regularized(X_cv_tensor)
    _, predicted_labels_cv = torch.max(outputs_cv, 1)
    num_miscategorized_cv = (predicted_labels_cv != y_cv_tensor).sum().item()
    miscategorization_rate_cv = num_miscategorized_cv / len(y_cv)

    miscategorization_rates_train.append(miscategorization_rate_train)
    miscategorization_rates_cv.append(miscategorization_rate_cv)

    # Update the minimum error rate lambda and its error rates
    if miscategorization_rate_cv < min_error_rate_cv:
        min_error_rate_lambda = regularization_value
        min_error_rate_train = miscategorization_rate_train
        min_error_rate_cv = miscategorization_rate_cv

# Plot graph of regularization values vs. misclassification rates
plt.plot(regularization_values, miscategorization_rates_train, label='Training Data')
plt.plot(regularization_values, miscategorization_rates_cv, label='CV Data')
plt.xlabel('Regularization Value (λ)')
plt.ylabel('Misclassification Rate')
plt.title('Regularization vs. Misclassification Rate')
plt.legend()
plt.show()

# Output the lowest error rate giving lambda and its error rates
print(f"Lowest error rate giving lambda: {min_error_rate_lambda}")
print(f"Error rate on Training Data: {min_error_rate_train:.2%}")
print(f"Error rate on CV Data: {min_error_rate_cv:.2%}")

'''