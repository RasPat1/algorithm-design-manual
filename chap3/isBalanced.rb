def isBalanced(value)
  balance = 0
  value.each_char do |char|
    char == '('  ? balance = balance + 1 : balance = balance - 1
    if balance < 0
      return false
    end
  end
  return balance == 0
end

tests = [
  {"value" => "(())", "isBalanced" => true},
  {"value" => ")(()", "isBalanced" => false},
  {"value" => "((())())()", "isBalanced" => true},
  {"value" => ")()(", "isBalanced" => false},
  {"value" => "()()()()()()((((()))))", "isBalanced" => true},
  {"value" => "()()()()()()((((())))", "isBalanced" => false},
  {"value" => "(((((()))", "isBalanced" => false},
  {"value" => "((())))", "isBalanced" => false}
]

for test in tests
  puts isBalanced(test["value"]) == test["isBalanced"] ? "Success" : "Fail"
end
