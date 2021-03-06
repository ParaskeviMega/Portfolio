library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity bit8_adder is 
	port (
		A: in std_logic_vector (7 downto 0);
		B: in std_logic_vector (7 downto 0);
		S: out std_logic_vector(8 downto 0));
end bit8_adder;

architecture RTL of bit8_adder is


begin
	S <= ('0' & A) + ('0' & B);


end RTL;
