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

    uint[] memory tempArr = arr;

    uint tempSum = 0;

    
    for (uint i = 0; i < tempArr.length; i++) {
        tempSum += tempArr[i]; // Update the sum after the loop completes
    }
    sum = tempSum; // Update the sum after the loop completes
}
}