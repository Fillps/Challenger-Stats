package sorting_algorithms;


import comparators.ComparatorDigitCatcher;
/*
 * Versao do Radix MSD que usa um vetor secundario array para separar por digitos.
 * Cria um valor chave para o indice, contando quantas vezes esse numero ocorreu.
 * Valores nulos serao armazenados no indice 0, exceto quando o array esta sendo ordenado inversamente.
 * Quando o valor sendo comparado e numerico, nao se usa o UTF-16, mas o valor do digito.
 * Quando o valor nao for numerico se usa UTF-16, criando um vetor int de 65539 (UFT-16 possui 65535, mas para armazenar 
 * os nulos e quando for inverso se adicionou + 4) para contar os caracteres. Apos indexar esse vetor, move os valores para o vetor 
 * secundario, depois copiando os valores para o vetor final. Apos isso, ele faz recursivamente todos os subarrays maiores que 1, com o proximo digito. 
 *   
 */
public class RadixSort extends SortingAlgorithmNonComparator {
	public static final String sigla = "RMSD";

	private static boolean numerico;
	private static int maxCharacterValue;
	private static int maxLength;
	
	public RadixSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, ComparatorDigitCatcher<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, ComparatorDigitCatcher<T> comparator){
		maxLength = 0;
		for (T t : array){
			maxLength = Math.max(maxLength, comparator.getLength(t));
		}
		numerico = false;
		if (comparator.toString().equals("numerico")){
			numerico = true;
			maxCharacterValue = 13;
			maxLength++; // caso o numero for negativo ele precisa mais um para o sinal
		}
		else{
			maxCharacterValue = Character.MAX_VALUE + 4;
		}
		msd(array, comparator, 0, array.length - 1, 0);

	}
	
	
	private static <T> void msd(T[] array, ComparatorDigitCatcher<T> comparator, int inferiorIndex, int superiorIndex, int digit){
		if (inferiorIndex >= superiorIndex || digit >= maxLength)
			return;
		T[] temp = array.clone();
		int[] cont = new int[maxCharacterValue];
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			int digitVallue = comparator.getDigitValue(array[i], digit, maxLength);
			cont[digitVallue + 2]++;
		}
		for (int i = 1; i < maxCharacterValue; i++){
			cont[i] += cont[i-1];
		}
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			int digitValue = comparator.getDigitValue(array[i], digit, maxLength) + 1;
			int contIndex = cont[digitValue]++ + inferiorIndex;
			temp[contIndex] = array[i];
		}
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			array[i] = temp[i];
		}
		if (numerico){         // se o valor sendo ordenao e numerico, 
			numerico = false;  //leva em consederacao o sinal dos numeros, ordenadno inversamete os numeros negativos
			msd(array, comparator.reversedDigitCatcher(), 0, cont[0] - 1, digit+1);
			msd(array, comparator.reversedDigitCatcher(), cont[maxCharacterValue-3], array.length - 1, digit+1);
			for (int i = 0; i < maxCharacterValue-3; i++){
				msd(array, comparator, cont[i] + inferiorIndex, cont[i+1]  + inferiorIndex - 1, digit+1);
			}
		}
		else {
			for (int i = 0; i < maxCharacterValue - 1; i++){
				msd(array, comparator, cont[i] + inferiorIndex, cont[i+1]  + inferiorIndex - 1, digit+1);
			}
		}
		
		
	}
	

	
	
}
