module MCPU_Alu(cmd,in1,in2,out,CF);
parameter CMD_SIZE=4;
parameter WORD_SIZE=16;

parameter  [CMD_SIZE-1:0]  CMD_AND  = 0; //2'b0000
parameter  [CMD_SIZE-1:0]  CMD_OR   = 1; //2'b0001
parameter  [CMD_SIZE-1:0]  CMD_XOR   = 2; //2'b0010
parameter  [CMD_SIZE-1:0]  CMD_ADD   = 3; //2'b0011
parameter  [CMD_SIZE-1:0]  CMD_LSL   = 4; //2'b0100
parameter  [CMD_SIZE-1:0]  CMD_LSR   = 5; //2'b0101
parameter  [CMD_SIZE-1:0]  CMD_SUB   = 11; //2'b1011

input [CMD_SIZE-1:0] cmd;
input [WORD_SIZE-1:0] in1;
input [WORD_SIZE-1:0] in2;
output[WORD_SIZE-1:0] out;
//carry flag
output CF;

wire [CMD_SIZE-1:0] cmd;
wire [WORD_SIZE-1:0] in1;
wire [WORD_SIZE-1:0] in2;
reg  [WORD_SIZE-1:0] out;
//carry flag
reg  CF;

always @ (cmd, in1, in2)
  #2
case(cmd)
  CMD_AND : begin
               out = in1&in2; 
      end
  CMD_OR : begin
              out = in1|in2;
            end
  CMD_XOR : begin
              out = in1^in2;
            end
  CMD_ADD : begin
              {CF,out} = in1+in2;
            end      
  CMD_LSL : begin
              out = in1<<in2;
            end
  CMD_LSR : begin
              out = in1>>in2;
            end
  CMD_LSL : begin
              out = in1>>in2;
            end
  default : begin
              {CF,out} = in1-in2;
            end
 endcase

endmodule