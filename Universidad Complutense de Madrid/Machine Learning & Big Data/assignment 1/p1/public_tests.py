import numpy as np
from linear_reg import compute_cost, compute_gradient, gradient_descent
from utils import load_data
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

def compute_cost_test(target):
    # print("Using X with shape (4, 1)")
    # Case 1
    x = np.array([2, 4, 6, 8]).T
    y = np.array([7, 11, 15, 19]).T
    initial_w = 2
    initial_b = 3.0
    cost = target(x, y, initial_w, initial_b)
    assert cost == 0, f"Case 1: Cost must be 0 for a perfect prediction but got {cost}"
    
    # Case 2
    x = np.array([2, 4, 6, 8]).T
    y = np.array([7, 11, 15, 19]).T
    initial_w = 2.0
    initial_b = 1.0
    cost = target(x, y, initial_w, initial_b)
    assert cost == 2, f"Case 2: Cost must be 2 but got {cost}"
    
    # print("Using X with shape (5, 1)")
    # Case 3
    x = np.array([1.5, 2.5, 3.5, 4.5, 1.5]).T   
    y = np.array([4, 7, 10, 13, 5]).T
    initial_w = 1
    initial_b = 0.0
    cost = target(x, y, initial_w, initial_b)
    assert np.isclose(cost, 15.325), f"Case 3: Cost must be 15.325 for a perfect prediction but got {cost}"
    
    # Case 4
    initial_b = 1.0
    cost = target(x, y, initial_w, initial_b)
    assert np.isclose(cost, 10.725), f"Case 4: Cost must be 10.725 but got {cost}"
    
    # Case 5
    y = y - 2
    initial_b = 1.0
    cost = target(x, y, initial_w, initial_b)
    assert  np.isclose(cost, 4.525), f"Case 5: Cost must be 4.525 but got {cost}"
    
    print("\033[92mAll tests passed!")
    
def compute_gradient_test(target):
    print("Using X with shape (4, 1)")
    # Case 1
    x = np.array([2, 4, 6, 8]).T
    y = np.array([4.5, 8.5, 12.5, 16.5]).T
    initial_w = 2.
    initial_b = 0.5
    dj_dw, dj_db = target(x, y, initial_w, initial_b)
    #assert dj_dw.shape == initial_w.shape, f"Wrong shape for dj_dw. {dj_dw} != {initial_w.shape}"
    assert dj_db == 0.0, f"Case 1: dj_db is wrong: {dj_db} != 0.0"
    assert np.allclose(dj_dw, 0), f"Case 1: dj_dw is wrong: {dj_dw} != [[0.0]]"
    
    # Case 2 
    x = np.array([2, 4, 6, 8]).T
    y = np.array([4, 7, 10, 13]).T + 2
    initial_w = 1.5
    initial_b = 1
    dj_dw, dj_db = target(x, y, initial_w, initial_b)
    #assert dj_dw.shape == initial_w.shape, f"Wrong shape for dj_dw. {dj_dw} != {initial_w.shape}"
    assert dj_db == -2, f"Case 1: dj_db is wrong: {dj_db} != -2"
    assert np.allclose(dj_dw, -10.0), f"Case 1: dj_dw is wrong: {dj_dw} != -10.0"   
    
    print("\033[92mAll tests passed!")
compute_cost_test(compute_cost)
compute_gradient_test(compute_gradient)
arr = load_data()
a = gradient_descent(arr[0], arr[1], 0, 0, cost_function=compute_cost, gradient_function=compute_gradient, alpha=0.01, num_iters=1500)
print("w: " + str(a[0]) + " b: " + str(a[1]))
    
'''J_history = a[2]  # J_history contains the cost at each iteration

# Plotting the cost function
plt.plot(range(len(J_history)), J_history, marker='o', linestyle='-')
plt.xlabel('Number of iterations')
plt.ylabel('Cost')
plt.title('Gradient Descent: Cost vs. Iterations')
plt.grid(True)
plt.show()



w_values = np.linspace(-10, 10, 100)
b_values = np.linspace(-10, 10, 100)

# Create a grid of w and b values
w_grid, b_grid = np.meshgrid(w_values, b_values)

# Compute the cost for each combination of w and b
cost_grid = np.zeros_like(w_grid)
for i in range(len(w_values)):
    for j in range(len(b_values)):
        cost_grid[i, j] = compute_cost(arr[0], arr[1], w_grid[i, j], b_grid[i, j])  # Change arr[0] and arr[1] with your data

# Plot the 3D surface
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.plot_surface(w_grid, b_grid, cost_grid, cmap='viridis')

# Set labels and title
ax.set_xlabel('w')
ax.set_ylabel('b')
ax.set_zlabel('Cost')
ax.set_title('Cost Function')

plt.show()



# Generate x values for the model function
x_model = np.linspace(min(arr[0]), max(arr[0]), 100)

# Compute corresponding y4 values using the model function and best-fit parameters
y_model = a[0] * x_model  + a[1]  # Replace best_fit_w and best_fit_b with your parameters

# Plot the scatter plot of the data with red crosses
plt.scatter(arr[0], arr[1], color='red', marker='x', label='Data')

# Plot the model function
plt.plot(x_model, y_model, color='blue', label='Model Function')



# Set labels and title
plt.xlabel('Population of City in 10,000s')
plt.ylabel('Profit in $10,000')
plt.title('Profits vs Population per City')
plt.legend()

plt.show()
'''