package d80harri.alphabeta.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import d80harri.alphabeta.intfs.IAgent;
import d80harri.alphabeta.intfs.IPlayer;
import d80harri.alphabeta.intfs.IPosition;
import d80harri.alphabeta.intfs.ITurn;

public class AbstractCmdAgent implements IAgent {
	protected static interface StringToTurnConverter {
		public ITurn convert(String str, IPlayer player);
	}

	private static class SticksStringToTurnConverter implements
			StringToTurnConverter {
		@Override
		public ITurn convert(String out, IPlayer player) {
			String[] split = out.split(" ");
			VectorTurn<Integer> result = new VectorTurn<Integer>(player,
					new Integer[] { Integer.parseInt(split[0]),
							Integer.parseInt(split[1]) });
			return result;
		}
	}

	private PrintStream ostream;
	private BufferedReader instream;
	private StringToTurnConverter converter = null;

	public AbstractCmdAgent(PrintStream ostream, BufferedReader instream, StringToTurnConverter converter) {
		this.ostream = ostream;
		this.instream = instream;
		this.converter = converter;
	}
	
	public AbstractCmdAgent(StringToTurnConverter converter){
		this(System.out, new BufferedReader(new InputStreamReader(System.in)), converter);
	}
	
	protected void finalize() throws Throwable {
		if (ostream != null) ostream.close();
		if (instream != null) instream.close();
	};

	private static String prompt(String msg, BufferedReader reader)
			throws IOException {
		System.out.println(msg);
		String result = reader.readLine();
		return result;
	}

	private static ITurn promptTurn(IPlayer human, BufferedReader reader,
			StringToTurnConverter converter) throws IOException {
		String out = prompt("Which turn? ", reader);
		return converter.convert(out, human);
	}

	@Override
	public ITurn calculateTurn(IPosition position) {
		ostream.println("Current position: ");
		ostream.println(position);
		ITurn turn = null;
		
		try {
			turn = promptTurn(position.getPlayerOnTurn(), instream, converter);
		} catch (IOException e) {
			throw new RuntimeException(); // TODO Exception
		}
		
		ostream.println(position.doTurn(turn));
		return turn;
	}

}
