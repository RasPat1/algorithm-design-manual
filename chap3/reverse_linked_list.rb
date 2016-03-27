class LL
  attr_accessor :nextNode, :value

  def initialize(nextNode, value)
    @nextNode = nextNode
    @value = value
  end
  def createChain
    l1 = LL.new(nil, "obj1")
    l2 = LL.new(l1, "obj2")
    l3 = LL.new(l2, "obj3")
    l4 = LL.new(l3, "obj4")
    l5 = LL.new(l4, "obj5")
    l6 = LL.new(l5, "obj6")
    l7 = LL.new(l6, "obj7")
  end
  def runTest
    puts "runTest"
    ll = LL.new(nil, "10")
    lChain = ll.createChain
    printChain lChain
    lChain = reverseChain lChain
    printChain lChain
  end
  def printChain lChain
    while lChain.nextNode != nil
      puts lChain.value
      lChain = lChain.nextNode
    end
  end
  def reverseChain lChain
    if lChain.nextNode == nil
      lChain
    end
    reverseChainImpl(nil, lChain, lChain.nextNode)
  end
  def reverseChainImpl(prev, current, nextNode)
    # prev = nil
    # current = lChain
    # nextNode = lChain.nextNode
    current.nextNode = prev
    # prev = current
    # current = nextNode
    if nextNode.nextNode == nil
      nextNode.nextNode = current
      nextNode
    else
      reverseChainImpl(current, nextNode, nextNode.nextNode)
      # nextNode = nextNode.nextNode
    end
  end

  def size
    lChain.nextNode != nil ? lChain.nextNode.size + 1 : 1
  end
  def last
    while lChain.nextNode != nil
      lChain = lChain.nextNode
    end
  end
end

lStatic = LL.new(nil,nil)
lStatic.runTest
