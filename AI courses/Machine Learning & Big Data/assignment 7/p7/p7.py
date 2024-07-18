import scipy.io as sio
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score
import numpy as np
import matplotlib.pyplot as plt
from utils import *
import codecs
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score
import os

'''
data = sio.loadmat('data\ex6data1.mat')
X = data['X']
y = data['y'].ravel()


svm = SVC(kernel='linear', C=100.0)
svm.fit(X, y)


def visualize_boundary(X, y, svm, file_name):
    x1 = np.linspace(X[:, 0].min(), X[:, 0].max(), 100)
    x2 = np.linspace(X[:, 1].min(), X[:, 1].max(), 100)
    x1, x2 = np.meshgrid(x1, x2)
    yp = svm.predict(np.array([x1.ravel(), x2.ravel()]).T).reshape(x1.shape)
    pos = (y == 1).ravel()
    neg = (y == 0).ravel()
    plt.figure()
    plt.scatter(X[pos, 0], X[pos, 1], color='black', marker='+')
    plt.scatter(
    X[neg, 0], X[neg, 1], color='yellow', edgecolors='black', marker='o')
    plt.contour(x1, x2, yp)
    plt.savefig(file_name)
    plt.close()

visualize_boundary(X=X, y=y, svm=svm, file_name="ex6data1-Fig 1.2.2.png")
'''

'''
data = sio.loadmat('data\ex6data2.mat')
X = data['X']
y = data['y'].ravel()

C = 1
sigma = 0.1

svm = SVC(kernel='rbf', C=C, gamma=1 / (2 * sigma**2))
svm.fit(X, y)


def visualize_boundary(X, y, svm, file_name):
    x1 = np.linspace(X[:, 0].min(), X[:, 0].max(), 100)
    x2 = np.linspace(X[:, 1].min(), X[:, 1].max(), 100)
    x1, x2 = np.meshgrid(x1, x2)
    yp = svm.predict(np.array([x1.ravel(), x2.ravel()]).T).reshape(x1.shape)
    pos = (y == 1).ravel()
    neg = (y == 0).ravel()
    plt.figure()
    plt.scatter(X[pos, 0], X[pos, 1], color='black', marker='+')
    plt.scatter(
    X[neg, 0], X[neg, 1], color='yellow', edgecolors='black', marker='o')
    plt.contour(x1, x2, yp)
    plt.savefig(file_name)
    plt.close()

visualize_boundary(X=X, y=y, svm=svm, file_name="ex6data1-Fig 1.3.png")
'''

'''
data = sio.loadmat('data\ex6data3.mat')
X = data['X']
y = data['y'].ravel()
Xval = data['Xval']
yval = data['yval'].ravel()

# Define the values of C and σ to try
C_values = [0.01, 0.03, 0.1, 0.3, 1, 3, 10, 30]
sigma_values = [0.01, 0.03, 0.1, 0.3, 1, 3, 10, 30]

best_score = 0
best_params = {'C': None, 'sigma': None}

# Loop through all combinations of C and σ
for C in C_values:
    for sigma in sigma_values:
        # Train the SVM with RBF kernel
        svm = SVC(kernel='rbf', C=C, gamma=1 / (2 * sigma**2))
        svm.fit(X, y)
        
        # Evaluate the SVM on the validation set
        score = svm.score(Xval, yval)
        
        # Update the best score and parameters if this model is better
        if score > best_score:
            best_score = score
            best_params['C'] = C
            best_params['sigma'] = sigma

# Train the best model on the entire dataset
best_svm = SVC(kernel='rbf', C=best_params['C'], gamma=1 / (2 * best_params['sigma']**2))
best_svm.fit(X, y)

print("Best parameters found:")
print("C:", best_params['C'])
print("sigma:", best_params['sigma'])
print("Validation Accuracy:", best_score)


def visualize_boundary(X, y, svm, file_name):
    x1 = np.linspace(X[:, 0].min(), X[:, 0].max(), 100)
    x2 = np.linspace(X[:, 1].min(), X[:, 1].max(), 100)
    x1, x2 = np.meshgrid(x1, x2)
    yp = svm.predict(np.array([x1.ravel(), x2.ravel()]).T).reshape(x1.shape)
    pos = (y == 1).ravel()
    neg = (y == 0).ravel()
    plt.figure()
    plt.scatter(X[pos, 0], X[pos, 1], color='black', marker='+')
    plt.scatter(
    X[neg, 0], X[neg, 1], color='yellow', edgecolors='black', marker='o')
    plt.contour(x1, x2, yp)
    plt.savefig(file_name)
    plt.close()

visualize_boundary(X=X, y=y, svm=best_svm, file_name="ex6data1-Fig 1.4.png")
'''




# Step 1: Load Data
spam_emails = []
easy_ham_emails = []
hard_ham_emails = []


for i in range(1, 500):
    email_contents = codecs.open('data_spam/spam/{:04d}.txt'.format(i), 'r', encoding='utf-8', errors='ignore').read()
    spam_emails = email2TokenList(email_contents)

for i in range(1, 2551):
    email_contents = codecs.open('data_spam/easy_ham/{:04d}.txt'.format(i), 'r', encoding='utf-8', errors='ignore').read()
    easy_ham_emails = email2TokenList(email_contents)

for i in range(1, 250):
    email_contents = codecs.open('data_spam/hard_ham/{:04d}.txt'.format(i), 'r', encoding='utf-8', errors='ignore').read()
    hard_ham_emails = email2TokenList(email_contents)

def create_feature_vector(email, vocab_dict):
    return [1 if word in email else 0 for word in vocab_dict]

# Step 3: Create feature vectors for each email
vocab_dict = getVocabDict()
spam_feature_vectors = [create_feature_vector(email, vocab_dict) for email in spam_emails]
easy_ham_feature_vectors = [create_feature_vector(email, vocab_dict) for email in easy_ham_emails]
hard_ham_feature_vectors = [create_feature_vector(email, vocab_dict) for email in hard_ham_emails]

# Step 2: Label Data
spam_labels = [1] * len(spam_feature_vectors)
easy_ham_labels = [0] * len(easy_ham_feature_vectors)
hard_ham_labels = [0] * len(hard_ham_feature_vectors)

# Step 3: Feature Extraction
# You can use the email2TokenList function and vocabulary list as before

# Step 4: Combine Data
emails = spam_feature_vectors + easy_ham_feature_vectors + hard_ham_feature_vectors
labels = spam_labels + easy_ham_labels + hard_ham_labels

# Step 5: Shuffle Data
# Combine emails and labels into a single list
data = list(zip(emails, labels))
# Shuffle the data
np.random.shuffle(data)
emails, labels = zip(*data)

# Step 6: Split Data
# Split the data into training and testing sets
train_size = int(0.8 * len(emails))
X_train, y_train = emails[:train_size], labels[:train_size]
X_test, y_test = emails[train_size:], labels[train_size:]


# Step 7: Train Model
# Train your SVM model using the training data
svm = SVC(kernel='linear', C=1.0)
svm.fit(X_train, y_train)

# Step 8: Evaluate Model
# Evaluate the performance of the trained model on the testing data
y_pred = svm.predict(X_test)

# Step 4: Evaluate Performance
accuracy = accuracy_score(y_test, y_pred)
precision = precision_score(y_test, y_pred)
recall = recall_score(y_test, y_pred)
f1 = f1_score(y_test, y_pred)

print("Accuracy:", accuracy)
print("Precision:", precision)
print("Recall:", recall)
print("F1-score:", f1)


import sys
sys.path.append(r'C:\Users\ardah\OneDrive\Masaüstü')  # Add the directory to the sys.path

'''
from p5 import nn  # Import the module

# Make a 2D array by copying the original array along the second dimension
y_train = np.squeeze(y_train)
y_train = np.array([np.where(y_train == c, 1, 0) for c in range(2)])

y_train = y_train.reshape(2, -1)

nn.test(X_train, y_train)
'''

'''
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
import torch
import torch.nn as nn
import torch.optim as optim

# Define the neural network architecture
model = nn.Sequential(
    nn.Linear(1899, 26, bias=True),
    nn.Sigmoid(),
    nn.Linear(26, 2, bias=True),
    nn.Sigmoid()
)   

#
#model = nn.Sequential(
##    nn.Linear(1899, 26),
#    nn.ReLU(),
#    nn.Linear(26, 2),
#    nn.ReLU()
#)   

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
        

print('Training finished.')

# Convert them to PyTorch tensors
X_test_tensor = torch.tensor(X_test, dtype=torch.float32)
y_test_tensor = torch.tensor(y_test, dtype=torch.long)

outputs = model(X_test_tensor)

# Get the predicted labels
_, predicted_labels = torch.max(outputs, 1)

accuracy = accuracy_score(y_test_tensor, predicted_labels)
print("Accuracy:", accuracy)
'''




import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import PolynomialFeatures, StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score

param_grid = {
    'degree': range(1, 20),  # Increase the range of degrees 
    'C': np.linspace(0.0001, 0.1, num=100)  # Adjust the range of regularization parameter C
}

best_accuracy = 0
best_degree = 1  # Initialize best_degree to the first degree value
best_C = None

#for degree in param_grid['degree']:
for C in param_grid['C']:
    
    # Transform data into polynomial features
    #poly_features = PolynomialFeatures(degree=degree, include_bias=True)
    poly_features = PolynomialFeatures(degree=1, include_bias=True)
    x_poly_train = poly_features.fit_transform(X_train)
    x_poly_test = poly_features.transform(X_test)

    # Scale data
    scaler = StandardScaler()
    x_poly_train_scaled = scaler.fit_transform(x_poly_train)
    x_poly_test_scaled = scaler.transform(x_poly_test)

    # Train Logistic Regression model
    model = LogisticRegression(C=C)
    model.fit(x_poly_train_scaled, y_train)

    # Predict validation set
    y_pred_test = model.predict(x_poly_test_scaled)

    # Compute accuracy
    accuracy = accuracy_score(y_test, y_pred_test)

    # Check if this is the best model so far
    if accuracy > best_accuracy:
        best_accuracy = accuracy
        #best_degree = degree
        best_C = C

print("Best Degree:", best_degree)
print("Best C:", best_C)

# Re-train the model with the best hyperparameters
poly_features = PolynomialFeatures(degree=best_degree, include_bias=True)
x_poly_train = poly_features.fit_transform(X_train)
x_poly_test = poly_features.transform(X_test)

scaler = StandardScaler()
x_poly_train_scaled = scaler.fit_transform(x_poly_train)
x_poly_test_scaled = scaler.transform(x_poly_test)

model = LogisticRegression(C=best_C)
model.fit(x_poly_train_scaled, y_train)

# Predict test set
y_pred_test = model.predict(x_poly_test_scaled)

# Calculate accuracy rate
accuracy_rate = accuracy_score(y_test, y_pred_test) * 100
print("Accuracy Rate:", accuracy_rate)