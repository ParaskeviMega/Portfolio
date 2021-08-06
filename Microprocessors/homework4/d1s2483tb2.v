module d1s2483tb2();
  
  reg tb_a;
  reg tb_b;
  reg tb_c;
  reg d_correct;
  reg temp;
  
  wire [2:0] tb_dut_inputs;

  wire tb_d;

  d1s2483 dut(tb_a, tb_b, tb_c, tb_d);

  assign tb_dut_inputs = {tb_a, tb_b, tb_c};

  	initial begin 
    {tb_a, tb_b, tb_c} = 3'b000;

    forever  #5 {tb_a, tb_b, tb_c} = tb_dut_inputs + 1;
  	end 
  	
  	initial begin 
    temp = 1'b0;
    
    forever begin
    #1 if ((tb_a  == 1) && (tb_b  == 1) &&  (tb_c == 0))
  	       temp <= 1;
	       else
 	        temp <= 0;
 	  end
  	end 
  	
  	initial begin 
    d_correct = 1'b1;
    
    forever begin
      #2 if (temp == tb_d)
 	         d_correct <= 1;
 	       else
 	         d_correct <= 0;  
 	  end
  	end 

endmodule