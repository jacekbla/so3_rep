package so3_rep;

public class OPT
{
	ReferenceString referenceString = new ReferenceString();
	Memory memory = new Memory();
	
	/*public OPT(ReferenceString referenceString, Memory memory)
	{
		this.referenceString = referenceString;
		this.memory = memory;
	}*/
	
	public Frame empty()
	{
		if(!memory.isEmpty())
		{
			Frame out = null;
			for(Frame frame: memory.memory)
			{
				if(frame.value == -1)
				{
					out = frame;
					break;
				}
			}
			return out;
		}
		else
		{
			return null;
		}
	}
	
	public boolean isInMemory(int value)
	{
		boolean out = false;
		for(Frame frame: memory.memory)
		{
			if(frame.value == value)
			{
				out = true;
				break;
			}
		}
		return out;
	}
	
	public Frame max()
	{
		if(!memory.isEmpty())
		{
			Frame out = memory.get(0);
			for(Frame frame: memory.memory)
			{
				if(frame.timeUntilUsed > out.timeUntilUsed)
				{
					out = frame;
				}
			}
			return out;
		}
		else
		{
			return null;
		}
	}
	
	public int opt()
	{
		int errorCounter = 0;
		for(int i = 0; i < referenceString.numberOfReferences; i++)
		{
			if(isInMemory(referenceString.get(i)) == true)
			{
				;
			}
			else
			{
				Frame temp = null;
				if(empty() == null)
				{
					temp = max();
				}
				else
				{
					temp = empty();
				}
				temp.value = referenceString.get(i);
				temp.age = 0;
				int j, k;
				k = 0;
				if(i < referenceString.size()-2)
				{
					j = i + 1;
					for(;j < referenceString.size()-1; j++)
					{
						k++;
						if(temp.value == referenceString.get(j))
						{
							break;
						}	
					}
					if(j == referenceString.size()-1)
					{
						k = 100 + i;
					}
					
				}
				errorCounter++;
				temp.timeUntilUsed = k;
			}
			for(Frame frame: memory.memory)
			{
				frame.timeUntilUsed--;
			}
			for(Frame frame: memory.memory)
			{
				frame.age++;
			}
			for(Frame f: memory.memory)
			{
				System.out.print(f.toString());
			}
			System.out.println("");
		}
		return errorCounter;
	}
	
	public static void main(String[] args)
	{
		OPT o = new OPT();
		System.out.println(o.referenceString.toString());
		System.out.println("braki stron: "+o.opt());
	}
}
