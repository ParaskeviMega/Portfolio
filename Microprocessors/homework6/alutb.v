module MCPU_Alutb();

parameter CMD_SIZE=2;
parameter WORD_SIZE=2;

reg [CMD_SIZE-1:0] opcode;
reg [WORD_SIZE-1:0] r1;
reg [WORD_SIZE-1:0] r2;
wire [WORD_SIZE*2-1:0] out;
reg [WORD_SIZE-1:0] temp;
reg iscorrect;
wire OVERFLOW;

MCPU_Alu #(.CMD_SIZE(CMD_SIZE), .WORD_SIZE(WORD_SIZE)) aluinst (opcode, r1, r2, out, OVERFLOW);

// Testbench code goes here
always #2 r1[0] = $random;
always #2 r2[0] = $random;
always #2 r1[1] = $random;
always #2 r2[1] = $random;

always #2 opcode[0] = $random;
always #2 opcode[1] = $random;

always @ (opcode or r1 or r2)
case(opcode)
  2'b00 : begin
              temp = r1 & r2; 
          end
  2'b01 : begin
              temp = r1 | r2;
          end
  2'b10 : begin
              temp = r1 ^ r2;
          end
  default : begin
              temp = r1 + r2;
            end
 endcase
 
 always @ (opcode or r1 or r2)
 if ((temp[0] == out[0]) && (temp[1] == out[1])) begin
   iscorrect =  1;
 end else begin
   iscorrect = 0;
 end

initial begin
  $display("@%0dns default is selected, opcode %b",$time,opcode);
end


endmodule
