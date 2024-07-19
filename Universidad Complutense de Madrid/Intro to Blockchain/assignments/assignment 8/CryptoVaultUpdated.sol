pragma solidity ^0.6.0;

contract VaultLib {
    address public owner;

    function init(address _owner) public {
        owner = _owner;
    }


    receive () external payable {
        revert("This contract does not accept direct ether transfers"); // Prevents direct ether transfers without function calls
}

}

contract CryptoVault {
    address public owner;
    uint prcFee;
    uint public collectedFees;
    address tLib;
    mapping (address => uint256) public accounts;

    bool private _locked;  // Mutex variable to prevent reentrancy

    modifier onlyOwner() {
        require(msg.sender == owner,"You are not the contract owner!");
        _;
    }

    constructor(address _vaultLib, uint _prcFee) public {
        tLib = _vaultLib;
        prcFee = _prcFee;
        (bool success,) = tLib.delegatecall(abi.encodeWithSignature("init(address)",msg.sender));
        require(success,"delegatecall failed");
    }

    function getBalance() public view returns(uint){
        return address(this).balance;
    }

    function deposit() public payable {
        require(msg.value >= 100, "Insufficient deposit");
        uint fee = msg.value * prcFee / 100;
        accounts[msg.sender] += msg.value - fee;
        collectedFees += fee;
    }

    function withdraw(uint _amount) public {
        uint currentBalance = accounts[msg.sender];
        require(currentBalance >= _amount, "Insufficient funds");

        require(!_locked, "Reentrancy guard: already locked");
        _locked = true; // added to prevent reentracy

        accounts[msg.sender] = currentBalance - _amount;  // Updated to prevent underflow

        _locked = false; // added to prevent reentracy

        (bool sent, ) = msg.sender.call{value: _amount}("");
        require(sent, "Failed to send funds");
    }
    
    function withdrawAll() public {
        uint amount = accounts[msg.sender];
        require(amount > 0, "Insufficient funds");
        (bool sent, ) = msg.sender.call{value: amount}("");
        require(sent, "Failed to send funds");
        accounts[msg.sender] = 0;
    }

    function collectFees() public onlyOwner {
        require(collectedFees > 0, "No fees collected");
        (bool sent, ) = owner.call{value: collectedFees}("");
        require(sent, "Failed to send fees");
        collectedFees = 0;
    }

    fallback () external payable {
        (bool success,) = tLib.delegatecall(msg.data);
        require(success,"delegatecall failed");
    }

    receive () external payable {
        revert("This contract does not accept direct ether transfers");
    }
}



