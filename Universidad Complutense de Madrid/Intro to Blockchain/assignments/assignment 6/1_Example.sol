// SPDX-License-Identifier: GPL-3.0

pragma solidity ^0.4.26;
contract lab6 {
uint[] arr;
uint sum;
function generate(uint n) external {
for (uint i = 0; i < n; i++) {
arr.push(i*i);
}
}
function computeSum() external {
sum = 0;
for (uint i = 0; i < arr.length; i++) {
sum = sum + arr[i];
}
}
}