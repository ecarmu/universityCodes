import numpy as np
from scipy.io import loadmat
from scipy.optimize import minimize
import utils as utils



def cost(theta1, theta2, X, y, lambda_):
    """
    Compute cost for 2-layer neural network. 

    Parameters
    ----------
    theta1 : array_like
        Weights for the first layer in the neural network.
        It has shape (2nd hidden layer size x input size + 1)

    theta2: array_like
        Weights for the second layer in the neural network. 
        It has shape (output layer size x 2nd hidden layer size + 1)

    X : array_like
        The inputs having shape (number of examples x number of dimensions).

    y : array_like
        1-hot encoding of labels for the input, having shape 
        (number of examples x number of labels).

    lambda_ : float
        The regularization parameter. 

    Returns
    -------
    J : float
        The computed value for the cost function. 

    """
    '''
    print(len(y), len(y[0]))
    print(len(X), len(X[0]))
    print(len(theta1), len(theta1[0]))
    print(len(theta2), len(theta2[0]))
    '''

    # adding bias to X
    '''X = np.insert(X, 0, 1, axis=1) 

    print(X[:, 0])

    estimations = np.zeros((len(y), len(X[0])))

    estimations = 1 / (1 + np.exp(-np.dot(theta1, X.T) )) 
    estimations = np.insert(estimations, 0, 1, axis=0)
    estimations = 1 / (1 + np.exp(-np.dot(theta2, estimations)))

    loss = (-y * np.log(estimations) - (1 - y) * np.log(1 - estimations))
    lambda_part = (lambda_ / (2 * len(y))) * np.sum(np.square(estimations))
    J = np.sum(loss) / len(y) + lambda_part
    '''

    m = len(X)
    
    # Compute hypothesis
    m = X.shape[0]
    a1 = np.hstack((np.ones((m, 1)), X))
    
    # Compute activations of the second layer
    z2 = np.dot(a1, theta1.T)
    a2 = 1 / (1 + np.exp(-z2))

    a2 = np.hstack((np.ones((m, 1)), a2))  # Add bias unit to hidden layer
    
    # Compute output layer
    z3 = np.dot(a2, theta2.T)
    h_theta = 1 / (1 + np.exp(-z3))

    
    # Transpose y
    y = y.T
    
    # Compute cost without regularization
    term1 = -y * np.log(h_theta)
    term2 = -(1 - y) * np.log(1 - h_theta)
    J = np.sum(term1 + term2) / m
    
    # Compute regularization term
    reg_term = (lambda_ / (2 * m)) * (np.sum(theta1[:, 1:]**2) + np.sum(theta2[:, 1:]**2))
    
    # Add regularization term to cost
    J += reg_term

    # output layer
     

    return J



def backprop(theta1, theta2, X, y, lambda_):
    """
    Compute cost and gradient for 2-layer neural network. 

    Parameters
    ----------
    theta1 : array_like
        Weights for the first layer in the neural network.
        It has shape (2nd hidden layer size x input size + 1)

    theta2: array_like
        Weights for the second layer in the neural network. 
        It has shape (output layer size x 2nd hidden layer size + 1)

    X : array_like
        The inputs having shape (number of examples x number of dimensions).

    y : array_like
        1-hot encoding of labels for the input, having shape 
        (number of examples x number of labels).

    lambda_ : float
        The regularization parameter. 

    Returns
    -------
    J : float
        The computed value for the cost function. 

    grad1 : array_like
        Gradient of the cost function with respect to weights
        for the first layer in the neural network, theta1.
        It has shape (2nd hidden layer size x input size + 1)

    grad2 : array_like
        Gradient of the cost function with respect to weights
        for the second layer in the neural network, theta2.
        It has shape (output layer size x 2nd hidden layer size + 1)

    """

    m = len(X)
    
    # Ensure y has the correct shape
    if y.shape[0] != m:
        y = y.T
    
    # Forward propagation
    a1 = np.hstack((np.ones((m, 1)), X))  # Add bias to input layer
    z2 = np.dot(a1, theta1.T)
    a2 = 1 / (1 + np.exp(-z2))  # Compute activation of hidden layer
    a2 = np.hstack((np.ones((m, 1)), a2))  # Add bias to hidden layer
    z3 = np.dot(a2, theta2.T)
    h_theta = 1 / (1 + np.exp(-z3))  # Output layer activations
    
    # Transpose y if it was transposed
    if y.shape[0] != m:
        y = y.T
    
    # Compute cost without regularization
    term1 = -y * np.log(h_theta)
    term2 = -(1 - y) * np.log(1 - h_theta)
    J = np.sum(term1 + term2) / m
    
    # Compute regularization term
    reg_term = (lambda_ / (2 * m)) * (np.sum(theta1[:, 1:]**2) + np.sum(theta2[:, 1:]**2))
    
    # Add regularization term to cost
    J += reg_term
    
    # Backpropagation
    delta3 = h_theta - y

    delta2 = np.dot(delta3, theta2[:, 1:]) * (a2[:, 1:] * (1 - a2[:, 1:]))  # Direct formula for sigmoid gradient
    
    # Compute gradients
    Delta1 = np.dot(delta2.T, a1)
    Delta2 = np.dot(delta3.T, a2)
    
    # Regularize gradients
    grad1 = Delta1 / m
    grad2 = Delta2 / m
    
    grad1[:, 1:] += (lambda_ / m) * theta1[:, 1:]
    grad2[:, 1:] += (lambda_ / m) * theta2[:, 1:]

    print(len(grad1), len(grad1[0]))
    print(len(grad2), len(grad2[0]))
    
    print(len(delta3), len(delta3[0]))
    print(len(theta2), len(theta2[0]))
    print(len(a2), len(a2[0]))
    print(len(delta2), len(delta2[0]))
    print(len(a1), len(a1[0]))
    
    print(len(Delta1), len(Delta1[0]))
    print(len(theta1), len(theta1[0]))
    

    return (J, grad1, grad2)


data = loadmat('data/ex3data1.mat', squeeze_me=True)
y = data['y']
X = data['X']

y = np.array([np.where(y == c, 1, 0) for c in range(10)])

weights = loadmat('data/ex3weights.mat', squeeze_me=True)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
theta1, theta2 = weights['Theta1'], weights['Theta2']

'''
val = cost(X=X, y=y, theta1=theta1, theta2=theta2, lambda_=1)
print(val)
'''

'''
utils.checkNNGradients(costNN=backprop, reg_param=1)

val, grad1, grad2 = backprop(X=X, y=y, theta1=theta1, theta2=theta2, lambda_=0)
print(val)



epsilon_init = 0.12
size_of_input_layer = 400
size_of_hidden_layer = 25
num_labels = 10
num_iterations = 1000
lambda_=1
alpha = 1

theta1 = np.random.random((size_of_hidden_layer, size_of_input_layer + 1)) * 2 * epsilon_init - epsilon_init
theta2 = np.random.random((num_labels, size_of_hidden_layer + 1)) * 2 * epsilon_init - epsilon_init


# Perform gradient descent
for i in range(num_iterations):
    # Perform forward propagation and backpropagation
    costt, grad1, grad2 = backprop(X=X, y=y, theta1=theta1, theta2=theta2, lambda_=lambda_)
    
    # Update parameters using gradient descent
    theta1 -= alpha * grad1
    theta2 -= alpha * grad2

print(y)

# Forward propagate to get predicted outputs
m = len(X)

# Forward propagate to get predicted outputs
a1 = np.hstack((np.ones((m, 1)), X))

# Compute activations of the second layer
z2 = np.dot(a1, theta1.T)
a2 = 1 / (1 + np.exp(-z2))
a2 = np.hstack((np.ones((m, 1)), a2))  # Add bias unit to hidden layer

# Compute output layer
z3 = np.dot(a2, theta2.T)
h_theta = 1 / (1 + np.exp(-z3))

# Convert predicted outputs to labels
predicted_labels = np.argmax(h_theta, axis=1) + 1

# Compare predicted labels with actual labels
correct_predictions = (predicted_labels == np.argmax(y, axis=0) + 1)

print(predicted_labels)
print(y)
print(correct_predictions)

# Calculate training accuracy
training_accuracy = np.mean(correct_predictions) * 100
    
print("Training Accuracy: {:.2f}%".format(training_accuracy))
'''
lambda_=1

def costt(theta):    
    # Compute cost using the neural network
    return (theta[0]-5)**2 + (theta[1]-5)**2

def grad(theta, X, y, lambda_):
    '''
    gradient = np.zeros(2);
    gradient[0] = 2*(theta[0]-5)
    gradient[1] = 2*(theta[1]-5)

    J, grad1, grad2 = backprop(theta1=gradient[0], theta2=gradient[1], X=X, y=y, lambda_=lambda_)

    return costt(theta), gradient
    '''


    theta1 = np.reshape(theta[:25 * (400 + 1)], (25, (400 + 1)))

    theta2 = np.reshape(theta[25 * (400 + 1):],(10, (25 + 1)))
    J, grad1, grad2 = backprop(theta1=theta1, theta2=theta2, X=X, y=y, lambda_=lambda_)
    grad = np.concatenate((np.ravel(grad1), np.ravel(grad2)))

    return J, grad

initial_theta = np.zeros(25 * 401 + 10 * 26)
result = minimize(fun=grad, x0=initial_theta, args=(X, y, lambda_), method='TNC', jac=True, options={'maxiter': 100})
print(result.x)



m = len(X)

# Forward propagate to get predicted outputs
a1 = np.hstack((np.ones((m, 1)), X))

# Compute activations of the second layer
z2 = np.dot(a1, theta1.T)
a2 = 1 / (1 + np.exp(-z2))
a2 = np.hstack((np.ones((m, 1)), a2))  # Add bias unit to hidden layer

# Compute output layer
z3 = np.dot(a2, theta2.T)
h_theta = 1 / (1 + np.exp(-z3))

# Convert predicted outputs to labels
predicted_labels = np.argmax(h_theta, axis=1) + 1

# Compare predicted labels with actual labels
correct_predictions = (predicted_labels == np.argmax(y, axis=0) + 1)

print(predicted_labels)
print(y)
print(correct_predictions)

# Calculate training accuracy
training_accuracy = np.mean(correct_predictions) * 100
    
print("Training Accuracy: {:.2f}%".format(training_accuracy))




def test(x_axis_size, y_axis_size):
    initial_theta = np.zeros(x_axis_size + y_axis_size)
    result = minimize(fun=grad, x0=initial_theta, args=(X, y, lambda_), method='TNC', jac=True, options={'maxiter': 100})
    print(result.x)



    m = len(X)

    # Forward propagate to get predicted outputs
    a1 = np.hstack((np.ones((m, 1)), X))

    # Compute activations of the second layer
    z2 = np.dot(a1, theta1.T)
    a2 = 1 / (1 + np.exp(-z2))
    a2 = np.hstack((np.ones((m, 1)), a2))  # Add bias unit to hidden layer

    # Compute output layer
    z3 = np.dot(a2, theta2.T)
    h_theta = 1 / (1 + np.exp(-z3))

    # Convert predicted outputs to labels
    predicted_labels = np.argmax(h_theta, axis=1) + 1

    # Compare predicted labels with actual labels
    correct_predictions = (predicted_labels == np.argmax(y, axis=0) + 1)

    print(predicted_labels)
    print(y)
    print(correct_predictions)

    # Calculate training accuracy
    training_accuracy = np.mean(correct_predictions) * 100
        
    print("Training Accuracy: {:.2f}%".format(training_accuracy))

print(len(X), len(X[0]))
print(len(y))
print(len(theta1), len(theta1[0]))
print(theta1[0:3, :])
print(len(theta2), len(theta2[0]))