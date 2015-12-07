function[TP] = TotalPers(M)

n = size(M,1);

TP = 0;

for i = 1:n
  TP = TP + (M(i,2) - M(i,1));
end

