# Define a threshold
threshold = 0.5  # Adjust this threshold as needed

# Convert predictions to binary (0 or 1) based on the threshold
y_pred_binary = np.where(np.abs(y_pred_test - y_test) < threshold, 1, 0)

# Calculate accuracy rate
accuracy_rate = np.mean(y_pred_binary == 1) * 100

print("Accuracy Rate:", accuracy_rate)
