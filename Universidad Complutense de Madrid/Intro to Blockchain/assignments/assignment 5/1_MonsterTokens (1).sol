// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.8.2 <0.9.0;

/**
 * @title Storage
 * @dev Store & retrieve value in a variable
 * @custom:dev-run-script ./scripts/deploy_with_ethers.ts
 */

library ArrayUtils {

    function contains(string[] memory arr, string memory val) public pure returns (bool){

        for(uint i = 0; i < arr.length; i++){
            if(keccak256(bytes(arr[i])) == keccak256(bytes(val)))
                return true;
        }

        return false;
    }

    function increment(uint[] storage arr, uint8 percentage) public {
        for(uint i = 0; i < arr.length; i++) {
            arr[i] += percentage;
        }
    }

    function sum(uint[] memory arr) public pure returns(uint){

        uint summ = 0; 

        for(uint i = 0; i < arr.length; i++) {
            summ += arr[i];
        }

        return summ;
    }

}

import "./ERC721simplified.sol";

contract MonsterTokens is ERC721simplified{

    address public owner;
    uint freshTokenId;
    mapping (uint => Character) characters;
    mapping (uint256 => address) tokenApprovals;

    constructor() {
        owner = msg.sender; // Set contract deployer as the owner
        freshTokenId = 10001;
    }

    struct Weapons {
        string[] names; // name of the weapon
        uint[] firePowers; // capacity of the weapon
    }

    struct Character {
        string name; // character name
        Weapons weapons; // weapons assigned to this character
        
        // ... you must add other fields for handling the token.
        uint tokenId;
        mapping(address => bool) tokenOwners;
        address base_tokenOwner;
    }

    function createMonsterToken(string memory characterName, address tokenOwner) external {
        require(msg.sender == owner, "This function can be only executed by the owner");

        Weapons memory weapons = Weapons(new string[](0), new uint[](0));
       // Initialize the struct without the mapping
        characters[freshTokenId].name = characterName;
        characters[freshTokenId].weapons = weapons;
        characters[freshTokenId].tokenId = freshTokenId;

        // Update the mapping separately
        characters[freshTokenId].tokenOwners[tokenOwner] = true;
        characters[freshTokenId].base_tokenOwner = tokenOwner;
        freshTokenId += 1;

        emit Transfer(address(0), tokenOwner, freshTokenId);
    }

    function addWeapon(uint characterTokenId, string memory weaponTypeName, uint weaponFirePower) external {

        require(characters[characterTokenId].tokenOwners[msg.sender], "Only token owner can execute this function");
   
        require(!(ArrayUtils.contains(characters[characterTokenId].weapons.names, weaponTypeName)), "Weapon with this name is already added");

        characters[characterTokenId].weapons.names.push(weaponTypeName);
        characters[characterTokenId].weapons.firePowers.push(weaponFirePower);
        
    }

    function incrementFirePower(uint tokenId, uint8 percentage) external {
        ArrayUtils.increment(characters[tokenId].weapons.firePowers, percentage);
    }

    function collectProfits() external {
        require(msg.sender == owner, "This function can be only executed by the owner");

        uint256 contractBalance = address(this).balance;
        require(contractBalance > 0, "No balance to collect");
        
        payable(owner).transfer(contractBalance);
    }

    function approve(address approved, uint256 tokenId) external override payable {
        require(characters[tokenId].tokenOwners[msg.sender], "Only token owner can approve");
        tokenApprovals[tokenId] = approved;
        emit Approval(msg.sender, approved, tokenId);
    }

    function transferFrom(address from, address to, uint256 tokenId) external override payable {
        require(from == msg.sender || tokenApprovals[tokenId] == msg.sender, "Not approved to transfer");
        require(characters[tokenId].tokenOwners[from], "Not the token owner");
        characters[tokenId].tokenOwners[from] = false;
        characters[tokenId].tokenOwners[to] = true;
        tokenApprovals[tokenId] = address(0);
        emit Transfer(from, to, tokenId);
    }

    function balanceOf(address ownerr) external view override returns (uint256) {
        uint256 balance = 0;
        for(uint i = 10001; i < freshTokenId; i++) {
            if (characters[i].tokenOwners[ownerr]) {
                balance++;
            }
        }
        return balance;
    }

    function ownerOf(uint256 tokenId) external view override returns (address) {
        require(tokenId >= 10001 && tokenId < freshTokenId, "Invalid token ID");
        for(uint i = 10001; i < freshTokenId; i++) {
            if (characters[i].tokenId == tokenId) {
                return characters[tokenId].base_tokenOwner;
            }
        }
        revert("Token not found");
    }

    function getApproved(uint256 tokenId) external view override returns (address) {
        require(tokenId >= 10001 && tokenId < freshTokenId, "Invalid token ID");
        return tokenApprovals[tokenId];
    }
}