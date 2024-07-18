import numpy as np
from scipy.io import loadmat
import utils

#########################################################################
# one-vs-all
#
def oneVsAll(X, y, n_labels, lambda_):
    """
     Trains n_labels logistic regression classifiers and returns
     each of these classifiers in a matrix all_theta, where the i-th
     row of all_theta corresponds to the classifier for label i.

     Parameters
     ----------
     X : array_like
         The input dataset of shape (m x n). m is the number of
         data points, and n is the number of features. 

     y : array_like
         The data labels. A vector of shape (m, ).

     n_labels : int
         Number of possible labels.

     lambda_ : float
         The logistic regularization parameter.

     Returns
     -------
     all_theta : array_like
         The trained parameters for logistic regression for each class.
         This is a matrix of shape (K x n+1) where K is number of classes
         (ie. `n_labels`) and n is number of features without the bias.
     """
    
    # for each model, I am initializing w and b values...   
    w_matrix = np.zeros((n_labels, len(X[0])))  # row 1 -> model 1    row 2 -> model 2
    b_matrix = np.zeros(n_labels) # column 1 -> model 1    column 2 -> model 2 

    y = np.array([np.where(y == c, 1, 0) for c in range(10)])

    for i in range(1000):

        estimations = np.dot(w_matrix, X.T) + b_matrix[:, np.newaxis]
        estimations = 1 / (1 + np.exp(-estimations))

        #print(len(estimations), len(estimations[0]))

        dj_db = np.sum(estimations - y) / len(y)
        dj_dw = np.dot(estimations - y, X) / len(y) + lambda_* (1 / len(y))*w_matrix
        
        w_matrix = w_matrix - 0.006 * dj_dw
        b_matrix = b_matrix - 0.006 * dj_db

        '''
        loss = ( (np.dot(np.log(estimations), -y.T)) - np.dot(np.log(1-estimations), 1 - y.T) ) * (1 / len(y) )
        loss = np.sum(loss)
        lambda_part = np.sum((lambda_ / (2*len(y)) ) * np.square(w_matrix))
        total_cost =  loss + lambda_part
        '''
        loss = (-y * np.log(estimations) - (1 - y) * np.log(1 - estimations))
        lambda_part = (lambda_ / (2 * len(y))) * np.sum(np.square(w_matrix))
        total_cost = np.sum(loss) / len(y) + lambda_part
        print(total_cost)

    all_theta = np.hstack((b_matrix.reshape(-1, 1), w_matrix))

    
    '''
    print("b_matrix:")
    print(len(b_matrix))
    print("w_mattix")
    print(len(w_matrix), len(w_matrix[0]))
    print("all_theta")
    print(len(all_theta), len(all_theta[0]))
    '''

    return all_theta


def predictOneVsAll(all_theta, X):
    """
    Return a vector of predictions for each example in the matrix X. 
    Note that X contains the examples in rows. all_theta is a matrix where
    the i-th row is a trained logistic regression theta vector for the 
    i-th class. You should set p to a vector of values from 0..K-1 
    (e.g., p = [0, 2, 0, 1] predicts classes 0, 2, 0, 1 for 4 examples) .

    Parameters
    ----------
    all_theta : array_like
        The trained parameters for logistic regression for each class.
        This is a matrix of shape (K x n+1) where K is number of classes
        and n is number of features without the bias.

    X : array_like
        Data points to predict their labels. This is a matrix of shape 
        (m x n) where m is number of data points to predict, and n is number 
        of features without the bias term. Note we add the bias term for X in 
        this function. 

    Returns
    -------
    p : array_like
        The predictions for each data point in X. This is a vector of shape (m, ).
    """

    '''print("all_theat: ")
    print(all_theta)
'''
    X_with_bias = np.insert(X, 0, 1, axis=1)

    probabilities = np.dot(all_theta, X_with_bias.T)

    p = np.argmax(probabilities, axis=0)

    return p


#########################################################################
# NN
#
def predict(theta1, theta2, X):
    """
    Predict the label of an input given a trained neural network.

    Parameters
    ----------
    theta1 : array_like
        Weights for the first layer in the neural network.
        It has shape (2nd hidden layer size x input size)

    theta2: array_like
        Weights for the second layer in the neural network. 
        It has shape (output layer size x 2nd hidden layer size)

    X : array_like
        The image inputs having shape (number of examples x image dimensions).

    Return 
    ------
    p : array_like
        Predictions vector containing the predicted label for each example.
        It has a length equal to the number of examples.
    """

    #print(len(X), len(X[0]))
    m = X.shape[0]
    X1s = np.hstack([np.ones((m, 1)), X])
    #print(len(X1s), len(X1s[0]))

    #print(len(theta1), len(theta1[0]))

    
    a1 = 1 / (1 + np.exp(-np.dot(theta1, X1s.T)))
    #print(len(a1), len(a1[1]))
    m = a1.shape[0]
    a1s = np.vstack([np.ones((1, a1.shape[1])), a1])
    #print(len(a1s), len(a1s[0]))
    pp = 1 / (1 + np.exp(-np.dot(theta2, a1s) )) 

    p = np.argmax(pp, axis=0)

    return p


data = loadmat('data/ex3data1.mat', squeeze_me=True)

X = data['X']
y = data['y']

rand_indices = np.random.choice(X.shape[0], 100, replace=False)
utils.displayData(X[rand_indices, :])


all_theta = oneVsAll(X=X, y=y, n_labels=10, lambda_=0)
predict_log_reg = predictOneVsAll(all_theta=all_theta, X=X)

weights = loadmat('data/ex3weights.mat')
theta1, theta2 = weights['Theta1'], weights['Theta2']
predict_neural_netw = predict(theta1=theta1, theta2=theta2, X=X)

'''
print("p: ")
print(p)
'''

#utils.displayData(X=predict_neural_netw)
print("y : " ,  len(y))
print("log reg: " + str(len(predict_log_reg)) + ", ")
print("neural net: " + str(len(predict_neural_netw)) + ", ")


# Count the number of matching elements
predictions_log_reg = np.sum(y == predict_log_reg)
predictions_neural_netw = np.sum(y == predict_neural_netw)

# Calculate the total number of elements
total_examples = len(y)

# Calculate the accuracy
accuracy_log_reg = (predictions_log_reg / total_examples) * 100.0
accuracy_neural_netw = (predictions_neural_netw / total_examples) * 100.0

print("Accuracy - logistic regression: ", accuracy_log_reg, "%")
print("Accuracy - neural network: " , accuracy_neural_netw, "%")