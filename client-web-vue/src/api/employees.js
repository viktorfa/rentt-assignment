const API_URL = 'http://localhost:7000/employees'

export const create = async employee => {
  const response = await fetch(API_URL, {
    method: 'POST',
    body: JSON.stringify(employee),
    headers: {
      'Content-Type': 'application/json'
    }
  })
  if (response.ok) {
    const responseJson = await response.json()
    return {
      ok: true,
      data: responseJson
    }
  }
}

export const deleteEmployee = async id => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  })
  if (response.ok) {
    return {
      ok: true,
    }
  }
}

export const list = async () => {
  const response = await fetch(API_URL, {
    method: 'GET',
  })
  if (response.ok) {
    const responseJson = await response.json()
    return {
      ok: true,
      data: responseJson
    }
  }
}

export const get = async id => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'GET',
  })
  if (response.ok) {
    const responseJson = await response.json()
    return {
      ok: true,
      data: responseJson
    }
  }
}

export const update = async employee => {
  try {
    const response = await fetch(`${API_URL}/${employee.id}`, {
      method: 'PATCH',
      body: JSON.stringify(employee)
    })
    if (response.ok) {
      return {
        ok: true,
      }
    }
  } catch (error) {
    return {
      ok: false,
      error,
    }
  }
}