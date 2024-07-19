from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import numpy as np
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score

classes = 6
m = 800
std = 0.4
centers = np.array([[-1, 0], [1, 0], [0, 1], [0, -1], [-2, 1], [-2, -1]])
X, y = make_blobs(n_samples=m, centers=centers, cluster_std=std,
random_state=2, n_features=2)

X_train, X_, y_train, y_ = train_test_split(X, y, test_size=0.50,
random_state=1)
X_cv, X_test, y_cv, y_test = train_test_split(X_, y_, test_size=0.20,
random_state=1)
# Function to plot data and decision boundaries
def plot_decision_boundary(X, y, model, title):
    h = 0.02  # step size in the mesh
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))
    Z = model.predict(np.c_[xx.ravel(), yy.ravel()])

    # Put the result into a color plot
    Z = Z.reshape(xx.shape)
    plt.contourf(xx, yy, Z, cmap=plt.cm.Paired)

    # Plot also the training points
    plt.scatter(X[:, 0], X[:, 1], c=y, edgecolors='k', cmap=plt.cm.Paired)
    plt.title(title)
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.show()

# Training and cross-validation plot
plt.figure(figsize=(12, 5))

# Plotting training data
plt.subplot(1, 2, 1)
plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=plt.cm.Set1, label='Training Data')
plt.scatter(X_cv[:, 0], X_cv[:, 1], c=y_cv, cmap=plt.cm.Set1, marker='^', label='CV Data')
plt.title('Training and CV Data')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()

# Ideal performance plot
plt.subplot(1, 2, 2)
# Fit a model on the entire training data
model = KNeighborsClassifier(n_neighbors=5)
model.fit(X_train, y_train)

# Plot decision boundaries
plot_decision_boundary(X_train, y_train, model, 'Ideal Performance (KNN)')

# Calculate accuracy on cross-validation data
cv_accuracy = accuracy_score(y_cv, model.predict(X_cv))
print(f'Cross-validation accuracy: {cv_accuracy:.2f}')

plt.tight_layout()
plt.show()