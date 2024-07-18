// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.0; // Do not change the compiler version.

interface IExecutableProposal {
    function executeProposal(uint proposalId, uint numVotes, uint numTokens) external payable;
}

contract Proposal is IExecutableProposal {

    uint256 public proposalId;
    uint256 public numVotes;
    uint256 public numTokens;

    // Implementation of the executeProposal function
    function executeProposal(uint256 _proposalId, uint256 _numVotes, uint256 _numTokens) external payable override {
        
        proposalId = _proposalId;
        numVotes = _numVotes;
        numTokens = _numTokens;

        // Emit an event or perform any other necessary logic
    }

    // Payable fallback function to receive ether transfers
    receive() external payable {}
}
