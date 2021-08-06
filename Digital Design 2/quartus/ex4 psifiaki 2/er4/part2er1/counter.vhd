library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity counter8bit is
	port(	CLK ,CLR, SET: in std_logic;
			Qout: out std_logic_vector (7 downto 0));			
end counter8bit;

architecture RTL of counter8bit is 

	signal counter: std_logic_vector(7 downto 0);
begin
	seq0:process(CLK, CLR, SET)
	begin
		if (CLR='1') then counter<="00000000";
		elsif (SET='1') then counter<="11111111";
		elsif (CLK'event and CLK='1') then counter <= counter + x"1";
		end if;
	end process;
	
Qout<=counter;
end RTL;