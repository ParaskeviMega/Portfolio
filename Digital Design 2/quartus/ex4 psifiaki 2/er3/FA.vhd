library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity FA is 
	port (
		A: in std_logic;
		B: in std_logic;
		Cin: in std_logic;
		S: out std_logic;
		Cout: out std_logic);
end FA;


architecture RTL of FA is
	signal wire1: std_logic;

begin 
	wire1<=A xor B;
	S<= wire1 xor Cin;
	Cout<= (A and B) or (Cin and wire1);
end RTL;