// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract VotingToken is ERC20 {
    address public owner;
    address public contractAddress;
    uint public a = 2;

    constructor(string memory name, string memory symbol) ERC20(name, symbol) {
        owner = msg.sender; // Set contract deployer as owner
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "Only owner can perform this action");
        _;
    }

    function copyVotingToken(address _contractAddress) external onlyOwner returns (VotingToken){
        require(contractAddress == _contractAddress, "Contract ");
    }

    // External function to mint new tokens for participants
    function mint(address account, uint256 amount) external onlyOwner {
        _mint(account, amount);
    }

    // Control who can burn tokens
    function burn(uint256 amount) external {
        _burn(msg.sender, amount);
    }

/*
    // Function to approve spending tokens by another contract
    function approve(address ownerr, address spender, uint256 amount) public returns (bool) {
        _approve(ownerr, spender, amount);
        return true;
    }
*/

    function approve(address owneerr, uint256 amount) public override returns (bool) {
        _approve(owneerr, msg.sender, amount);
        return true;
    }

}
