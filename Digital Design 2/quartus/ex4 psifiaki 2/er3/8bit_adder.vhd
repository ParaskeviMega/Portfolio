library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity bit8_adder is 
	port (
		A: in std_logic_vector (7 downto 0);
		B: in std_logic_vector (7 downto 0);
		Cin: in std_logic;
		S: out std_logic_vector(7 downto 0);
		Cout: out std_logic );
end bit8_adder;


architecture RTL of bit8_adder is

component FA is
  port (
    A: in std_logic;
	B: in std_logic;
	Cin: in std_logic;
	S: out std_logic;
	Cout: out std_logic);
end component;

signal C : std_logic_vector(6 downto 0);



begin 
	
	u0: FA port map (A(0), B(0), Cin, S(0), C(0));
	u1: FA port map (A(1), B(1), C(0), S(1), C(1));
	u2: FA port map (A(2), B(2), C(1), S(2), C(2));
	u3: FA port map (A(3), B(3), C(2), S(3), C(3));
	u4: FA port map (A(4), B(4), C(3), S(4), C(4));
	u5: FA port map (A(5), B(5), C(4), S(5), C(5));
	u6: FA port map (A(6), B(6), C(5), S(6), C(6));
	u7: FA port map (A(7), B(7), C(6), S(7), Cout);
	
end RTL;


