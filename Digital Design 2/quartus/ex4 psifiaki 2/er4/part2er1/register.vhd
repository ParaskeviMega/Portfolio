library IEEE;
use IEEE.std_logic_1164.all;

entity dfreg is
	port(	CLK ,CLR, SET: in std_logic;
			D: in std_logic_vector (7 downto 0);
			Qout: out std_logic_vector (7 downto 0));			
end dfreg;

architecture RTL of dfreg is 

	signal Q: std_logic_vector(7 downto 0);
begin
	seq0:process(CLK, CLR, SET)
	begin
		if (CLR='1') then Q<="00000000";
		elsif (SET='1') then Q<="11111111";
		elsif (CLK'event and CLK='1') then Q<=D;
		end if;
	end process;
	
Qout<=Q;
end RTL;