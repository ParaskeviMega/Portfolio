module MCPU_RAMControllertb();

parameter WORD_SIZE=8;
parameter ADDR_WIDTH=8;
parameter RAM_SIZE=1<<ADDR_WIDTH;

MCPU_RAMController #(.WORD_SIZE(WORD_SIZE), .ADDR_WIDTH(ADDR_WIDTH), .RAM_SIZE(RAM_SIZE)) aluinst (opcode, r1, r2, out, OVERFLOW);
